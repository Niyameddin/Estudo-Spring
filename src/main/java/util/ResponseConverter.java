package util;

import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * Created by guilherme on 26/09/15.
 */
@Service
public class ResponseConverter {
    public static final String SUCCESS = "SUCCESS";
    public static final String WARNING = "WARNING";
    public static final String ERROR = "ERROR";

    private StringBuilder errorJson;

    public ResponseConverter() {
        this.errorJson = new StringBuilder();
    }

    public String convertErrorListToJson(List<ObjectError> elementErrorList, String messageStatus){
        clearJsonString();
        errorJson.append("{");
        int errNumber = 0;
        for(ObjectError elementError : elementErrorList){
            errorJson.append("\""+errNumber+"\":"+"{");
            errorJson.append("\"status\":"+"\""+messageStatus+"\",");
            errorJson.append("\"objectType\":"+"\""+elementError.getObjectName()+"\",");
            errorJson.append("\"errorCode\":"+"\""+elementError.getCode()+"\",");
            errorJson.append("\"fieldCode\":"+"\""+elementError.getCodes()[0]+"\",");
            errorJson.append("\"defaultMessage\":"+"\""+elementError.getDefaultMessage()+"\"");
            errorJson.append("}");
            errorJson.append(",");
            errNumber++;
        }
        errorJson.deleteCharAt(errorJson.length()-1);
        errorJson.append("}");
        return errorJson.toString();
    }

    public String convertMessageToJson(Object obj, String defaultMessage, String messageStatus){
        clearJsonString();
        String objectType = obj.getClass().getSimpleName().replaceFirst
                (obj.getClass().getSimpleName().substring(0,1),
                        obj.getClass().getSimpleName().substring(0,1).toUpperCase());
        errorJson.append("{");
            errorJson.append("\"0\":"+"{");
                errorJson.append("\"status\":"+"\""+messageStatus+"\",");
                errorJson.append("\"objectType\":"+"\""+objectType+"\",");
                errorJson.append("\"objectAttributes\":"+obj.toString()+",");
                errorJson.append("\"defaultMessage\":"+"\""+defaultMessage+"\"");
            errorJson.append("}");
        errorJson.append("}");
        return errorJson.toString();
    }

    private void clearJsonString(){
        this.errorJson.delete(0,errorJson.length());
    }
}
