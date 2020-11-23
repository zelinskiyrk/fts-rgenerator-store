package {{path}}.{{entity.name}}.service;

import {{path}}.{{entity.name}}.api.request.Add{{entity.nameUpper}}Request;
import {{path}}.{{entity.name}}.api.request.{{entity.nameUpper}}Request;
import {{path}}.{{entity.name}}.exception.{{entity.nameUpper}}ExistException;
import {{path}}.{{entity.name}}.exception.{{entity.nameUpper}}NotExistException;
import {{path}}.{{entity.name}}.model.{{entity.nameUpper}}Doc;
import {{path}}.{{entity.name}}.repository.{{entity.nameUpper}}Repository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class {{entity.nameUpper}}ApiService {
    private final {{entity.nameUpper}}Repository {{entity.name}}Repository;
    private final MongoTemplate mongoTemplate;

    public {{entity.nameUpper}}Doc add{{entity.nameUpper}}(Add{{entity.nameUpper}}Request request) throws {{entity.nameUpper}}ExistException {
        if ({{entity.name}}Repository.find{{entity.nameUpper}}DocBy{{entity.nameUpper}}Name(request.get{{entity.nameUpper}}()).isPresent() == true){
            throw new {{entity.nameUpper}}ExistException();
        }
        {{entity.nameUpper}}Doc {{entity.name}}Doc = {{entity.nameUpper}}Mapping.getInstance().getRequest().convert(request);
        return {{entity.name}}Doc;
    }

    public Optional<{{entity.nameUpper}}Doc> findById(ObjectId id){
        return {{entity.name}}Repository.findById(id);
    }

    public List<{{entity.nameUpper}}Doc> search(){
        return {{entity.name}}Repository.findAll();
    }

    public {{entity.nameUpper}}Doc update({{entity.nameUpper}}Request request) throws {{entity.nameUpper}}NotExistException {
        Optional<{{entity.nameUpper}}Doc> {{entity.name}}DocOptional = {{entity.name}}Repository.findById(request.getId());
        if ({{entity.name}}DocOptional.isPresent() == false){
            throw new {{entity.nameUpper}}NotExistException();
        }

        {{entity.nameUpper}}Doc {{entity.name}}Doc = {{entity.nameUpper}}Mapping.getInstance().getRequest().convert(request);
        {{entity.name}}Doc.setId(request.getId());
        {{entity.name}}Repository.save({{entity.name}}Doc);

        return {{entity.name}}Doc;
    }

    public void delete(ObjectId id){
        {{entity.name}}Repository.deleteById(id);
    }
}
