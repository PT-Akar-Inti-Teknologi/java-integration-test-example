package org.ait.project.guideline.example.shared.openfeign.reqres;

import org.ait.project.guideline.example.config.openfeign.OpenFeignReqresConfig;
import org.ait.project.guideline.example.shared.openfeign.reqres.request.ReqresCreateUserRequest;
import org.ait.project.guideline.example.shared.openfeign.reqres.response.ReqresCreateUserResponse;
import org.ait.project.guideline.example.shared.openfeign.reqres.response.ReqresListUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
    value = "reqres-client",
    url = "${restclient.url.reqres-client}",
    configuration = OpenFeignReqresConfig.class,
    fallback = ReqresClientFallback.class
)
public interface ReqresClient {

  @GetMapping("/api/users")
  ReqresListUserResponse getListUser(@RequestParam(required = false) String page);

  ReqresCreateUserResponse createUserReqres(@RequestBody ReqresCreateUserRequest reqresCreateUserRequest);
}
