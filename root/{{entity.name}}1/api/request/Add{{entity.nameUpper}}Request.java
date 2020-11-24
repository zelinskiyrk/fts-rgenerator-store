package {{path}}.{{entity.name}}.api.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "Add{{entity.nameUpper}}Request", description = "Model for add {{entity.name}}")
public class Add{{entity.nameUpper}}Request {
    {{#entityProperties}}
        {{level}} {{type}} {{name}};
    {{/entityProperties}}
}
