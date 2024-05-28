package com.in28minutes.rest.webservice.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
    // URI 버전 관리
    @GetMapping("v1/person")
    public PersonV1 getFirstVersionOfPerson(){
        return new PersonV1("Bob Charile");
    }

    @GetMapping("v2/person")
    public PersonV2 getSecondVersionOfPerson(){
        return new PersonV2(new Name("Bob", "Charlie"));
    }
    // RequestParameter를 사용
    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getFirstVersionOfPersonRequestParameter(){
        return new PersonV1("Bob Carlie");
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getSecondVersionOfPersonRequestParameter(){
        return new PersonV2(new Name("Bob Carlie", "Charlie"));
    }
    // header를 이용한 방법
    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionOfPersonRequestHeader(){
        return new PersonV1("Bob Carlie");
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionOfPersonRequestHeader(){
        return new PersonV2(new Name("Bob Carlie", "Charlie"));
    }
    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getFirstVersionOfPersonRequestAccept(){
        return new PersonV1("Bob Carlie");
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionOfPersonRequestAccept(){
        return new PersonV2(new Name("Bob Carlie", "Charlie"));
    }

}
