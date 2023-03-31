package com.fastcampus.finalprojectbe.global.response;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    public <T> SingleResponse<T> getSingleResponse(T data){
        SingleResponse<T> res = new SingleResponse<>();
        res.setData(data);
        res.setCode(200);
        res.setMessage("Success");
        res.setSuccess(true);
        return res;
    }

    public <T> ListResponse<T> getListResponse(List<T> list){
        ListResponse<T> res = new ListResponse<>();
        res.setList(list);
        res.setCode(200);
        res.setMessage("Success");
        res.setSuccess(true);
        return res;
    }

    public CommonResponse getSuccessResponse(){
        CommonResponse res = new CommonResponse();
        res.setSuccess(true);
        res.setCode(200);
        res.setMessage("Success");
        return res;
    }

    public CommonResponse getFailResponse(int code, String msg){
        CommonResponse res = new CommonResponse(code,msg);
        res.setSuccess(false);
        res.setCode(code);
        res.setMessage(msg);
        return res;
    }

}