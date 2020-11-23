package {{path}}.{{entity.name}}.api.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@ApiModel(value = "{{entity.nameUpper}}Response", description = "{{entity.nameUpper}} data (for search and List)")
public class {{entity.nameUpper}}Response {
    {{#entityProperties}}
        protected {{type}} {{name}};
    {{/entityProperties}}
}
