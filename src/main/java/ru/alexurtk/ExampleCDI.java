package ru.alexurtk;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;
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

    private String size;

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

    public void checkPassword(){
        loginSuccess = exampleEJB.checkPassword(login, password);
    }

    public void createUser(){
        createSuccess = exampleEJB.createUser(login, password);
    }

    public List<UserEntity> getAllUsers(){
        return exampleEJB.getAllUsers();
    }

    public void uploadFile(FileUploadEvent event){

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrect", null));

        System.out.println();

    }

    public void startSomething(){
        System.out.println();
    }

    public void downloadXml() throws IOException {

        Workbook book = new XSSFWorkbook(new FileInputStream("reference.xlsm"));

        Sheet sheet = book.getSheet("Настройки");

        Row row = sheet.getRow(5);
        Cell cell = row.getCell(1);

        cell.setCellValue("1123");

        book.write(new FileOutputStream("test.xlsm"));
    }
}
