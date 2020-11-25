package {{path}}.{{entity.name}}.service;

import {{path}}.base.api.request.SearchRequest;
import {{path}}.base.api.response.SearchResponse;
import {{path}}.{{entity.name}}.api.request.{{entity.nameUpper}}Request;
import {{path}}.{{entity.name}}.exception.{{entity.nameUpper}}ExistException;
import {{path}}.{{entity.name}}.exception.{{entity.nameUpper}}NotExistException;
import {{path}}.{{entity.name}}.mapping.{{entity.nameUpper}}Mapping;
import {{path}}.{{entity.name}}.model.{{entity.nameUpper}}Doc;
import {{path}}.{{entity.name}}.repository.{{entity.nameUpper}}Repository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class {{entity.nameUpper}}ApiService {
    private final {{entity.nameUpper}}Repository {{entity.name}}Repository;
    private final MongoTemplate mongoTemplate;

    public {{entity.nameUpper}}Doc add{{entity.nameUpper}}({{entity.nameUpper}}Request request)
            throws {{entity.nameUpper}}ExistException, CityNotExistException {

        if ({{entity.name}}Repository.findByCityIdAnd{{entity.nameUpper}}Name(request.getCityId(), request.get{{entity.nameUpper}}Name()).isPresent() == true){
            throw new {{entity.nameUpper}}ExistException();
        }

        {{entity.nameUpper}}Doc {{entity.name}}Doc = {{entity.nameUpper}}Mapping.getInstance().getRequest().convert(request);
        {{entity.name}}Repository.save({{entity.name}}Doc);
        return {{entity.name}}Doc;
    }

    public Optional<{{entity.nameUpper}}Doc> findById(ObjectId id){
        return {{entity.name}}Repository.findById(id);
    }

    public SearchResponse<{{entity.nameUpper}}Doc> search(
            SearchRequest request
    ){
        Query query = new Query();
        if (request.getCityId() != null) {
            query.addCriteria(Criteria.where("cityId").is(request.getCityId()));
        }
        Long count = mongoTemplate.count(query, {{entity.nameUpper}}Doc.class);

        List<{{entity.nameUpper}}Doc> {{entity.name}}Docs = mongoTemplate.find(query, {{entity.nameUpper}}Doc.class);
        return SearchResponse.of({{entity.name}}Docs, count);
    }

    public {{entity.nameUpper}}Doc update({{entity.nameUpper}}Request request) throws {{entity.nameUpper}}NotExistException {
        Optional<{{entity.nameUpper}}Doc> {{entity.name}}DocOptional = {{entity.name}}Repository.findById(request.getId());
        if ({{entity.name}}DocOptional.isPresent() == false){
            throw new {{entity.nameUpper}}NotExistException();
        }

        {{entity.nameUpper}}Doc oldDoc = {{entity.name}}DocOptional.get();

        {{entity.nameUpper}}Doc {{entity.name}}Doc = {{entity.nameUpper}}Mapping.getInstance().getRequest().convert(request);
        {{entity.name}}Doc.setId(request.getId());
        {{entity.name}}Repository.save({{entity.name}}Doc);

        return {{entity.name}}Doc;
    }

    public void delete(ObjectId id){
        {{entity.name}}Repository.deleteById(id);
    }
}
