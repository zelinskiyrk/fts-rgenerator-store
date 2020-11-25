package {{path}}.{{entity.name}}.api.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@ApiModel(value = "{{entity.nameUpper}}Request", description = "Model for create and update {{entity.name}}")
public class {{entity.nameUpper}}Request {
        private ObjectId id;
        private String {{entity.name}}Name;
}