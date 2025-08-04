/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.time.LocalDateTime;

/**
 *
 * @author Natalie Ulate Rojas
 */
public class ErrorLog {
    
     private int log_id;
    private LocalDateTime logTimestamp;
    private String errorMessage;
    private String methodName;

    public ErrorLog() {
    }
    /*
      Constructor para crear un registro de error con todos sus datos.
      @param log_id El ID único del registro de error.
      @param logTimestamp La fecha y hora exactas del error.
      @param errorMessage El mensaje de la excepción capturada.
      @param methodName El nombre del método donde se originó el error.
     */
    public ErrorLog(int log_id, LocalDateTime logTimestamp, String errorMessage, String methodName) {
        this.log_id = log_id;
        this.logTimestamp = logTimestamp;
        this.errorMessage = errorMessage;
        this.methodName = methodName;
    }

    public int getLog_id() {
        return log_id;
    }

    public void setLog_id(int log_id) {
        this.log_id = log_id;
    }

    public LocalDateTime getLogTimestamp() {
        return logTimestamp;
    }

    public void setLogTimestamp(LocalDateTime logTimestamp) {
        this.logTimestamp = logTimestamp;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    
}
