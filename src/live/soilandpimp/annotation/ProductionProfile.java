package live.soilandpimp.annotation;

import org.springframework.context.annotation.Profile;

import live.soilandpimp.util.AppConstants;

@Profile(AppConstants.PRODUCTION_PROFILE)
public @interface ProductionProfile {}