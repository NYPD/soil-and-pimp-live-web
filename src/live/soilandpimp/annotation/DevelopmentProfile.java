package live.soilandpimp.annotation;

import org.springframework.context.annotation.Profile;

import live.soilandpimp.util.AppConstants;

@Profile(AppConstants.DEVELOPMENT_PROFILE)
public @interface DevelopmentProfile {}