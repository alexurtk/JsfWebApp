package ru.alexurtk;

/**
 * Created by alex on 10.05.2018.
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.PrimeFaces;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.StreamedContent;
import ru.alexurtk.entity.Car;
import ru.alexurtk.entity.Theme;
import ru.alexurtk.service.CarService;

@ManagedBean(name="dtSelectionView")
@ViewScoped
public class SelectionView implements Serializable {

    private List<Car> cars1;
    private List<Car> cars2;
    private List<Car> cars3;
    private List<Car> cars4;
    private List<Car> cars5;
    private List<Car> cars6;
    private Car selectedCar;
    private List<Car> selectedCars;
    private String testImg;
    private String mobilePhone;
    private List<Theme> selectedThemes;
    private List<Theme> themes;
    private Integer intValue;
    private String maxIntValue;
    private String testSOM;
    private boolean switchCheckBox;
    private boolean switchCheckBoxFlag;
    private boolean checked;
    private String keyedinContent;


    /****/
    private String id;
    private String name;

    public String getId() { return id;}

    public void setId(String id) { this.id = id;
        if(this.id.equals("1"))
            name = "Cesar";
        else
            name = "Loachamin";
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public boolean isTstBtnDisabled(){
        return StringUtils.isEmpty(id);
    }
    /****/




    @ManagedProperty("#{carService}")
    private CarService service;

    @PostConstruct
    public void init() {

        switchCheckBoxFlag = false;

        maxIntValue = "10";

        cars1 = service.createCars(4);
        cars2 = service.createCars(10);
        cars3 = service.createCars(10);
        cars4 = service.createCars(10);
        cars5 = service.createCars(10);
        cars6 = service.createCars(10);
        testImg = "<br><img src=\"previous_16.png\"/>";


        themes = new ArrayList<Theme>();
        themes.add(new Theme(0, "Afterdark", "afterdark"));
        themes.add(new Theme(1, "Afternoon", "afternoon"));
        themes.add(new Theme(2, "Afterwork", "afterwork"));
        themes.add(new Theme(3, "Aristo", "aristo"));
        themes.add(new Theme(4, "Black-Tie", "black-tie"));
        themes.add(new Theme(5, "Blitzer", "blitzer"));
        themes.add(new Theme(6, "Bluesky", "bluesky"));
        themes.add(new Theme(7, "Bootstrap", "bootstrap"));
        themes.add(new Theme(8, "Casablanca", "casablanca"));
        themes.add(new Theme(9, "Cupertino", "cupertino"));
        themes.add(new Theme(10, "Cruze", "cruze"));
        themes.add(new Theme(11, "Dark-Hive", "dark-hive"));
        themes.add(new Theme(12, "Delta", "delta"));
        themes.add(new Theme(13, "Dot-Luv", "dot-luv"));
        themes.add(new Theme(14, "Eggplant", "eggplant"));
        themes.add(new Theme(15, "Excite-Bike", "excite-bike"));
        themes.add(new Theme(16, "Flick", "flick"));
        themes.add(new Theme(17, "Glass-X", "glass-x"));
        themes.add(new Theme(18, "Home", "home"));
        themes.add(new Theme(19, "Hot-Sneaks", "hot-sneaks"));
        themes.add(new Theme(20, "Humanity", "humanity"));
        themes.add(new Theme(21, "Le-Frog", "le-frog"));
        themes.add(new Theme(22, "Midnight", "midnight"));
        themes.add(new Theme(23, "Mint-Choc", "mint-choc"));
        themes.add(new Theme(24, "Overcast", "overcast"));
        themes.add(new Theme(25, "Pepper-Grinder", "pepper-grinder"));
        themes.add(new Theme(26, "Redmond", "redmond"));
        themes.add(new Theme(27, "Rocket", "rocket"));
        themes.add(new Theme(28, "Sam", "sam"));
        themes.add(new Theme(29, "Smoothness", "smoothness"));
        themes.add(new Theme(30, "South-Street", "south-street"));
        themes.add(new Theme(31, "Start", "start"));
        themes.add(new Theme(32, "Sunny", "sunny"));
        themes.add(new Theme(33, "Swanky-Purse", "swanky-purse"));
        themes.add(new Theme(34, "Trontastic", "trontastic"));
        themes.add(new Theme(35, "UI-Darkness", "ui-darkness"));
        themes.add(new Theme(36, "UI-Lightness", "ui-lightness"));
        themes.add(new Theme(37, "Vader", "vader"));


        cars2 = service.createCars(10);
    }

    public String getKeyedinContent() {
        return keyedinContent;
    }

    public void testKeyedinContent(){
        System.out.println("keyedinContent: "+keyedinContent);
    }

    public void setKeyedinContent(String keyedinContent) {
        this.keyedinContent = keyedinContent;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void switchFlag(){
        switchCheckBoxFlag = !switchCheckBoxFlag;
        System.out.println("switchCheckBoxFlag: "+switchCheckBoxFlag);
        System.out.println("switchCheckBox: "+switchCheckBox);
    }

    public void submitThemes() {
        System.out.println(selectedThemes);
    }

    public boolean isSwitchCheckBox() {
        return switchCheckBox;
    }

    public void setSwitchCheckBox(boolean switchCheckBox) {
        this.switchCheckBox = switchCheckBox;
    }

    public boolean isSwitchCheckBoxFlag() {
        return switchCheckBoxFlag;
    }

    public void setSwitchCheckBoxFlag(boolean switchCheckBoxFlag) {
        this.switchCheckBoxFlag = switchCheckBoxFlag;
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public List<Car> getCars1() {
        return cars1;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    public String getTestSOM() {
        return testSOM;
    }

    public void setTestSOM(String testSOM) {
        this.testSOM = testSOM;
    }

    public String getMaxIntValue() {
        return maxIntValue;
    }

    public void setMaxIntValue(String maxIntValue) {
        this.maxIntValue = maxIntValue;
    }

    public List<Theme> getSelectedThemes() {
        return selectedThemes;
    }

    public void setSelectedThemes(List<Theme> selectedThemes) {
        this.selectedThemes = selectedThemes;
    }

    public String getMobilePhone() {
        System.out.println("getMobilePhone "+mobilePhone);
        return mobilePhone;
    }

    public boolean checkPhone(){
        System.out.println("mobilePhone "+mobilePhone);
        return StringUtils.isEmpty(mobilePhone);
    }

    public void block(){
        System.out.println("block "+mobilePhone);
        mobilePhone = "";
    }

    public void setMobilePhone(String mobilePhone) {
        System.out.println("getMobilePhone "+mobilePhone);
        this.mobilePhone = mobilePhone;
    }

    public void addCar(){
        cars1.add(service.getRandomCar());
        System.out.println(cars1.size());
    }

    public void testBackUpdate1(){
//        addCar();
        PrimeFaces.current().ajax().update("form:basicDT");
    }

    public void testBackUpdate2(){
//        addCar();
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("form:basicDT");
    }

    public void testBackUpdate3(){
//        addCar();
        PrimeFaces.current().ajax().update("form:basicDT");

    }


    public List<Car> getCars2() {
        return cars2;
    }

    public List<Car> getCars3() {
        return cars3;
    }

    public List<Car> getCars4() {
        return cars4;
    }

    public List<Car> getCars5() {
        return cars5;
    }

    public List<Car> getCars6() {
        return cars6;
    }

    public void setService(CarService service) {
        this.service = service;
    }

    public Car getSelectedCar() {
        return selectedCar;
    }

    public void setSelectedCar(Car selectedCar) {
        this.selectedCar = selectedCar;
    }

    public String getTestImg() {
        return testImg;
    }

    public void setTestImg(String testImg) {
        this.testImg = testImg;
    }

    public List<Car> getSelectedCars() {
        return selectedCars;
    }

    public void setSelectedCars(List<Car> selectedCars) {
        this.selectedCars = selectedCars;
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Car Selected", ((Car) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Car Unselected", ((Car) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        System.out.println(cars2.get(event.getRowIndex()).getYear());

        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void testt() {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "LOL1: "+selectedCar.getId(), null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void testEmpty() {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "LOL TEST", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void uploadFile(FileUploadEvent event){
        Car foo = (Car) event.getComponent().getAttributes().get("foo");

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "LOL2: "+foo.getId()+" "+foo.getBrand(), null);

        FacesContext.getCurrentInstance().addMessage(null, msg);
        System.out.println();
    }



    public void setSelCar(Car car){
        this.selectedCar = car;

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "LOL1: "+selectedCar.getId(), null);
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void justTest(){
        System.out.println("cars1.size(): "+cars1.size());
    }


    public StreamedContent streamFile(){
        addCar();

        System.out.println("cars1.size(): "+cars1.size());
        System.out.println("123");

        RequestContext context = RequestContext.getCurrentInstance();
        context.update("@form");
        context.update("basicDT");

        Workbook book = null;
        try {
            book = new XSSFWorkbook(new FileInputStream("reference.xlsm")); //wildfly-12.0.0.Final\wildfly-12.0.0.Final\bin

            FacesContext facesContext = FacesContext.getCurrentInstance();

            // Get HTTP response
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

            //response.reset();   // Reset the response in the first place
//        response.setHeader("Content-Type", "application/xls");
            response.setHeader("Content-Disposition", "attachment; filename="+"reference1.xlsm");
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setContentType("application/octet-stream");

            response.flushBuffer();

            book.write(response.getOutputStream());

        } catch (IOException e) {
            System.out.println("error");
        }


        return null;

    }

    public void t1(){

    }

    public void t2(){

    }

}
