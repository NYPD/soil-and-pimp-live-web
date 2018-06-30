package live.soilandpimp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import live.soilandpimp.domain.EmailSubscription;
import live.soilandpimp.domain.Event;
import live.soilandpimp.model.EventForm;
import live.soilandpimp.model.HomeEvents;
import live.soilandpimp.repository.EmailRepository;
import live.soilandpimp.repository.EventsRepository;
import live.soilandpimp.util.AppConstants;

@Service
public class DefaultMainService implements MainService {

    @Autowired
    private EventsRepository eventsRepository;
    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private Environment environment;
    @Autowired
    private Mailer mailer;

    @Override
    public HomeEvents getHomeEvents() {

        List<Event> activeEvents = new ArrayList<>();
        List<Event> upcomingEvents = new ArrayList<>();

        Iterable<Event> allEvents = eventsRepository.findAll();

        for (Event event : allEvents) {

            boolean eventActive = event.isEventActive();
            if (eventActive) {
                activeEvents.add(event);
                continue;
            }

            boolean eventUpcoming = event.isEventUpcoming();

            if (eventUpcoming) upcomingEvents.add(event);
        }

        return new HomeEvents(activeEvents, upcomingEvents);
    }

    @Override
    public List<Event> getPastEvents() {

        Iterable<Event> allEvents = eventsRepository.findAll();

        List<Event> pastEvents = new ArrayList<>();

        for (Event event : allEvents)
            if (!event.isEventUpcoming()) pastEvents.add(event);

        Collections.sort(pastEvents, Event.FIRST_SCHEDULE_DATE_ORDER_DESC);

        return pastEvents;
    }

    @Override
    public List<Event> getAllEvents() {

        List<Event> allEvents = new ArrayList<>();
        eventsRepository.findAll().forEach(event -> allEvents.add(event));

        return allEvents;
    }

    @Override
    public Event getEvent(String eventKey) {

        if (eventKey == null) return null;

        Optional<Event> event = eventsRepository.findById(eventKey);
        return event.isPresent()? event.get() : null;
    }

    @Override
    public void deleteEvent(String eventKey) {
        eventsRepository.deleteById(eventKey);
    }

    @Override
    public Event saveEvent(EventForm eventForm) {

        boolean isNewEvent = eventForm.getEventKey() == null;

        Event event = null;

        if (isNewEvent) {
            event = new Event(eventForm);
        } else {
            event = eventsRepository.findById(eventForm.getEventKey()).get();
            event.updateEvent(eventForm);
            eventsRepository.save(event);
        }

        eventsRepository.save(event);

        return event;
    }

    @Override
    @Transactional
    public void addEmailSubscription(String emailAddress) {

        EmailSubscription emailSubscription = new EmailSubscription(emailAddress);
        emailRepository.save(emailSubscription);

        String subject = "SOIL & \"PIMP\" LIVE email verification";
        String htmlText = this.verifyEmailMarkup(emailSubscription);

        Email email = EmailBuilder.startingBlank()
                                  .from("events@soilandpimp.live")
                                  .to(emailAddress)
                                  .withSubject(subject)
                                  .withHTMLText(htmlText)
                                  .buildEmail();

        mailer.sendMail(email);

    }

    @Override
    public boolean verifyEmailSubscription(String emailAddress, String userVerificationToken) {

        EmailSubscription emailSubscription = emailRepository.findById(emailAddress).get();

        //There is not even this email address in the DB
        if (emailSubscription == null) return false;

        boolean emailVerified = emailSubscription.verifyEmailAddress(userVerificationToken);
        if (emailVerified) emailRepository.save(emailSubscription);

        return emailVerified;
    }

    @Override
    public void emailUnsubscribe(String emailAddress) {
        boolean existsById = emailRepository.existsById(emailAddress);
        if (existsById) emailRepository.deleteById(emailAddress);
    }

    private String verifyEmailMarkup(EmailSubscription emailSubscription) {
        
        List<String> activeProfiles = Arrays.asList(environment.getActiveProfiles());
        boolean isProduction = activeProfiles.contains(AppConstants.PRODUCTION_PROFILE);

        String url = isProduction? AppConstants.PROD_URL : AppConstants.DEV_URL;
        String emailAddress = emailSubscription.getEmailAddress();
        String verificationToken = emailSubscription.getVerificationToken();

        StringBuffer stringBuffer = new StringBuffer();
        
        stringBuffer.append("<!DOCTYPE html>");
        stringBuffer.append("<html>");
        stringBuffer.append("<head>");
        stringBuffer.append("<meta charset=\"utf-8\">");
        stringBuffer.append("</head>");
        stringBuffer.append("<body>");
        
        stringBuffer.append("<p>");
        stringBuffer.append("We need to verify your email to complete your SOIL & \"PIMP\" LIVE subscription.");
        stringBuffer.append("</p>");
        
        //TODO finish dumb button
        stringBuffer.append("<a style=\"color:#fff;background-color:#449d44;border-color: #398439;"
                            + "text-decoration: none;padding: 6px 12px;font-size:16px;font-weight:400;border-radius:4px;"
                            + "line-height:1.42857143;border:1px solid transparent;\"");

        stringBuffer.append("href=\"" + url + "/verify-email?email=" + emailAddress + "&token=" + verificationToken + "\"");
        stringBuffer.append(">");
        stringBuffer.append("Verify Email Address");
        stringBuffer.append("</a>");

        stringBuffer.append("<p>");
        stringBuffer.append("If you have not signed up for SOIL & \"PIMP\" LIVE event notifications, please ignore this email.");
        stringBuffer.append("<br>");
        stringBuffer.append("ありがと.");
        stringBuffer.append("</p>");
        
        stringBuffer.append("</body>");
        stringBuffer.append("</html>");

        return stringBuffer.toString();
    }

}
