package core;

public class EndPointsUtils {

        public static class reqResEndPoints{
                public static final String MULTIPLE_USERS_LIST_GET = "/api/users?page=2";
                public static final String SINGLE_LIST_OF_USERS_GET = "/api/users/2";
                public static final String SINGLE_USER_NOT_FOUND_GET = "/api/users/23";
                public static final String LIST_RESOURCE_GET = "/api/unknown";
                public static final String SINGLE_RESOURCE_GET = "/api/unknown/2";
                public static final String RESOURCE_NOT_FOUND_GET = "/api/unknown/23";
                public static final String DELAY_API_GET(int delayInSeconds) {
                        return "/api/users?delay=" + delayInSeconds;
                }
                public static final String USER_DETAILS_POST = "/api/users";
                public static final String REGISTER_POST = "/api/register";
                public static final String LOGIN_POST = "/api/login";


        }

        public static class fakeStoreAPI {
                public static final String LOGIN_POST = "/users";
                public static final String LOGIN_CHECK = "/auth/login";

        }
}
