package util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * Created by guilherme on 26/09/15.
 */
@Service
public class ResponseConverter {

    private StringBuilder errorJson;

    public ResponseConverter() {
        this.errorJson = new StringBuilder();
    }

    public String convertResponseMessage(List<ObjectError> elementErrorList){
        clearJsonString();
        errorJson.append("{");
        for(ObjectError elementError : elementErrorList){
            errorJson.append("\"objectName\":"+"\""+elementError.getObjectName()+"\",");
            errorJson.append("\"errorCode\":"+"\""+elementError.getCode()+"\",");
            errorJson.append("\"fieldCode\":"+"\""+elementError.getCodes()[0]+"\",");
            errorJson.append("\"defaultMessage\":"+"\""+elementError.getDefaultMessage()+"\"");
        }
        errorJson.append("}");
        return errorJson.toString();
    }

    public String convertResponseMessage(String objectName, String errorCode, String fieldCode, String defaultMessage){
        clearJsonString();
        return "";
    }

    public String convertResponseMessage(String fieldCode, String defaultMessage){
        clearJsonString();
        return "";
    }

    private void clearJsonString(){
        this.errorJson.delete(0,errorJson.length());
    }
}
