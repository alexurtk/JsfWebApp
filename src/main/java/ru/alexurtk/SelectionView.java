package ru.alexurtk;

/**
 * Created by alex on 10.05.2018.
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.StreamedContent;
import ru.alexurtk.entity.Car;
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

    @ManagedProperty("#{carService}")
    private CarService service;

    @PostConstruct
    public void init() {
        cars1 = service.createCars(10);
        cars2 = service.createCars(10);
        cars3 = service.createCars(10);
        cars4 = service.createCars(10);
        cars5 = service.createCars(10);
        cars6 = service.createCars(10);
    }

    public List<Car> getCars1() {
        return cars1;
    }


    public void addCar(){
        cars1.add(service.getRandomCar());
        System.out.println(cars1.size());
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

    public void testt() {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "LOL1: "+selectedCar.getId(), null);
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


    public StreamedContent streamFile(){
        addCar();

        System.out.println("123");

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
            e.printStackTrace();
        }




        return null;

    }


}
