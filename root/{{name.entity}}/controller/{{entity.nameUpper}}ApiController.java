package {{path}}.{{entity.name}}.controller;

import {{path}}.base.api.request.SearchRequest;
import {{path}}.base.api.response.OkResponse;
import {{path}}.base.api.response.SearchResponse;
import {{path}}.{{entity.name}}.api.request.{{entity.nameUpper}}Request;
import {{path}}.{{entity.name}}.api.response.{{entity.nameUpper}}Response;
import {{path}}.{{entity.name}}.exception.{{entity.nameUpper}}ExistException;
import {{path}}.{{entity.name}}.exception.{{entity.nameUpper}}NotExistException;
import {{path}}.{{entity.name}}.mapping.{{entity.nameUpper}}Mapping;
import {{path}}.{{entity.name}}.routes.{{entity.nameUpper}}ApiRoutes;
import {{path}}.{{entity.name}}.service.{{entity.nameUpper}}ApiService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Api(value = "{{entity.nameUpper}} API")
public class {{entity.nameUpper}}ApiController {
    private final {{entity.nameUpper}}ApiService {{entity.name}}ApiService;

    @PostMapping({{entity.nameUpper}}ApiRoutes.ADMIN)
    @ApiOperation(value = "Add new {{entity.name}}", notes = "Use this if you need to add a new {{entity.name}}. You need administrator rights to add a {{entity.name}}.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "{{entity.nameUpper}} already exist")
    })
    public OkResponse<{{entity.nameUpper}}Response> add{{entity.nameUpper}}(@RequestBody {{entity.nameUpper}}Request request) throws {{entity.nameUpper}}ExistException, CityNotExistException {
        return OkResponse.of({{entity.nameUpper}}Mapping.getInstance().getResponse().convert(
                {{entity.name}}ApiService.add{{entity.nameUpper}}(request)));
    }

    @GetMapping({{entity.nameUpper}}ApiRoutes.BY_ID)
    @ApiOperation(value = "Find {{entity.name}} by ID", notes = "Use this if you need to find a {{entity.name}}.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "{{entity.nameUpper}} not found")
    })
    public OkResponse<{{entity.nameUpper}}Response> byId(
            @ApiParam(value = "{{entity.nameUpper}} ID")
            @PathVariable ObjectId id) throws ChangeSetPersister.NotFoundException {
        return OkResponse.of({{entity.nameUpper}}Mapping.getInstance().getResponse().convert(
                {{entity.name}}ApiService.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new)
        ));
    }

    @GetMapping({{entity.nameUpper}}ApiRoutes.ROOT)
    @ApiOperation(value = "Search all cities", notes = "Use this if you want to list all cities.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    public OkResponse<SearchResponse<{{entity.nameUpper}}Response>> search(
            @ModelAttribute SearchRequest request
            ){
        return OkResponse.of({{entity.nameUpper}}Mapping.getInstance().getSearch().convert(
                {{entity.name}}ApiService.search(request)
        ));
    }

    @PutMapping({{entity.nameUpper}}ApiRoutes.ADMIN_BY_ID)
    @ApiOperation(value = "Update {{entity.name}} by ID", notes = "Use this if you want to update {{entity.name}}. You need administrator rights to update a {{entity.name}}.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "{{entity.nameUpper}} ID invalid")
    })
    public OkResponse<{{entity.nameUpper}}Response> updateById(
            @ApiParam(value = "{{entity.nameUpper}} ID")
            @PathVariable String id,
            @RequestBody {{entity.nameUpper}}Request {{entity.name}}Request
            ) throws {{entity.nameUpper}}NotExistException {
        return OkResponse.of({{entity.nameUpper}}Mapping.getInstance().getResponse().convert(
                {{entity.name}}ApiService.update({{entity.name}}Request)
        ));
    }

    @DeleteMapping({{entity.nameUpper}}ApiRoutes.ADMIN_BY_ID)
    @ApiOperation(value = "Delete {{entity.name}} by ID", notes = "Use this if you want to delete {{entity.name}}. You need administrator rights to delete a {{entity.name}}.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    public OkResponse<String> deleteById(
            @ApiParam(value = "{{entity.nameUpper}} ID")
            @PathVariable ObjectId id){
        {{entity.name}}ApiService.delete(id);
        return OkResponse.of(HttpStatus.OK.toString());
    }
}
