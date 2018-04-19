package ru.alexurtk;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import ru.alexurtk.entity.UserEntity;

/**
 * Created by alex on 11.04.2018.
 */
@Named
@Stateless
public class ExampleCDI {
    private String login;
    private String password;

    private boolean loginSuccess;
    private boolean createSuccess;

    private String radioValue;
    private String radioValue2;

    private String inputText;

    private String size;

    private String uploadFile;
    private String handleFileUpload;

    public String getHandleFileUpload() {
        return handleFileUpload;
    }

    public void setHandleFileUpload(String handleFileUpload) {
        this.handleFileUpload = handleFileUpload;
    }

    @EJB
    private ExampleEJB exampleEJB;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoginSuccess() {
        return loginSuccess;
    }

    public void setLoginSuccess(boolean loginSuccess) {
        this.loginSuccess = loginSuccess;
    }

    public boolean isCreateSuccess() {
        return createSuccess;
    }

    public void setCreateSuccess(boolean createSuccess) {
        this.createSuccess = createSuccess;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(String uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getRadioValue() {
        return radioValue;
    }

    public void setRadioValue(String radioValue) {
        this.radioValue = radioValue;
    }

    public String getRadioValue2() {
        return radioValue2;
    }

    public void setRadioValue2(String radioValue2) {
        this.radioValue2 = radioValue2;
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public void checkPassword(){
        loginSuccess = exampleEJB.checkPassword(login, password);
    }

    public void createUser(){
        createSuccess = exampleEJB.createUser(login, password);
    }

    public List<UserEntity> getAllUsers(){
        return exampleEJB.getAllUsers();
    }

    public void handleFileUpload(FileUploadEvent event){

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrect", null));

        System.out.println();

    }

    public void startSomething(){
        System.out.println("lol");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrect", null));

//        FacesMessage msg = new FacesMessage("Model reset, but it won't work properly.");
//        FacesContext.getCurrentInstance().addMessage(null, msg);
        List<FacesMessage> listMsgs = FacesContext.getCurrentInstance().getMessageList();
        Iterator<FacesMessage> itMsgs = FacesContext.getCurrentInstance().getMessages();

        Iterator<String> itIds = FacesContext.getCurrentInstance().getClientIdsWithMessages();

        while (itIds.hasNext()) {
            List<FacesMessage> messageList = FacesContext.getCurrentInstance().getMessageList(itIds.next());
            if (!messageList.isEmpty()) { // if empty, it will be unmodifiable and throw UnsupportedOperationException...
                messageList.clear();
            }
        }

        System.out.println();
    }

    public void resetFail(){
        FacesMessage msg = new FacesMessage("Model reset, but it won't work properly.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void reset(){
        PrimeFaces.current().resetInputs("form:panel");
    }

    public void addReqMessage(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Нужно добавить", null));
    }

    public StreamedContent downloadXml() throws IOException {

        System.out.println("123");

        return null;
    }

    public StreamedContent streamFile(){
        System.out.println("123");

        Workbook book = null;
        try {
            book = new XSSFWorkbook(new FileInputStream("reference.xlsm"));


        Sheet sheet = book.getSheet("Настройки");

        Row row = sheet.getRow(5);
        Cell cell = row.getCell(1);

        cell.setCellValue("1123");

        ///////


        FacesContext facesContext = FacesContext.getCurrentInstance();

        // Get HTTP response
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

        //response.reset();   // Reset the response in the first place
//        response.setHeader("Content-Type", "application/xls");
        response.setHeader("Content-Disposition", "attachment; filename="+"reference1.xlsm");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setContentType("application/octet-stream");

        response.flushBuffer();

//        IOUtils.copy(str)
        book.write(response.getOutputStream());

            //todo выяснить причину возникновения ошибки
            // Caused by: java.lang.IllegalStateException: UT010006: Cannot call getWriter(), getOutputStream() already called
            //если пошагово пройтись, то не возникает, появляется если запустить без остановок

//        OutputStream responseOutputStream = response.getOutputStream();


//        book.write(responseOutputStream);
//
//        responseOutputStream.flush();
//        responseOutputStream.close();
//
//        facesContext.responseComplete();



        } catch (IOException e) {
            e.printStackTrace();
        }




        return null;

    }
}
