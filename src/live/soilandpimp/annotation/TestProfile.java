package live.soilandpimp.annotation;

import org.springframework.context.annotation.Profile;

import live.soilandpimp.util.AppConstants;

@Profile(AppConstants.TEST_PROFILE)
public @interface TestProfile {}