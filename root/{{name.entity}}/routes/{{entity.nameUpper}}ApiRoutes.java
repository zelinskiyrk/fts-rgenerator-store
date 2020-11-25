package {{path}}.{{entity.name}}.routes;

import {{path}}.base.routes.BaseApiRoutes;

public class {{entity.nameUpper}}ApiRoutes {
    public static final String ADMIN = BaseApiRoutes.ADMIN + "/{{entity.name}}";
    public static final String ADMIN_BY_ID = ADMIN + "/{id}";
    public static final String ROOT = BaseApiRoutes.V1 + "/{{entity.name}}";
    public static final String BY_ID = ROOT + "/{id}";
}
