package live.soilandpimp.domain.enums;

import java.lang.annotation.Annotation;

import live.soilandpimp.annotation.GoogleLogin;

public enum ApiType {

    GOOGLE(GoogleLogin.class);

    private final Class<? extends Annotation> apiLoginServiceClass;

    private ApiType(Class<? extends Annotation> clazz) {
        this.apiLoginServiceClass = clazz;
    }

    /**
     * Finds the ApiType by name without throwing an exception like enum.valueOf(). If no ApiType is
     * found, return null.
     * 
     * @param name - The name to find by
     * @return ApiType
     */
    public static ApiType findByName(String name) {

        for (ApiType apiType : values())
            if (apiType.name().equals(name)) return apiType;

        return null;
    }

    public Class<? extends Annotation> getApiLoginServiceClass() {
        return apiLoginServiceClass;
    }

}
