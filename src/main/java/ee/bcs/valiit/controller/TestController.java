package ee.bcs.valiit.controller;

import ee.bcs.valiit.Lesson1MathUtil;
import ee.bcs.valiit.Lesson2;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    private final List<Employee> employees = new ArrayList();

    @GetMapping("employees")   //Töötajate nimekirja väljastamine (GET)
    public List<Employee> getAllEmployees() {
        return employees;
    }
    //localhost:8080/employees - TÖÖTAB

    @GetMapping("employee/{id}")   //Ühe töötaja andmete saamine (GET)
    public Employee oneEmployee(@PathVariable("id") int id) {
        return employees.get(id);
    }
    //localhost:8080/employee/5 - TÖÖTAB
    //5. töötaja andmed

    //@PutMapping Postman programmi kaudu
    @PutMapping("employee/{id}") //Ühe töötaja andmete muutmine (PUT)
    public void changeEmployee(@PathVariable("id") int id, @RequestBody Employee employee) {
        employees.set(id, employee);
    }
    //localhost:8080/employee/1 - TÖÖTAB
    // mitmenda indeksi töötaja andmed muuta tahad

    //PostMapping Postman programmi kaudu
    @PostMapping("employee") //Ühe töötaja andmete lisamine (POST), Post puhul @PostMapping
    public void addEmployee(@RequestBody Employee employee) {    //void, ei tagasta midagi
        employees.add(employee);                                //GetMapping ja RequestBody ei käi kokku!!!
    }
    //localhost:8080/employee - TÖÖTAB
    // indeksit juurde ei tule, lisab listi töötaja

    //DeleteMapping läbi Postmani. Vali vasakult rippmenüüst "Delete"
    // Sisesta õige URL ja siis"Send", siis kustutab {id} elemendi
    @DeleteMapping("employee/{id}")   //Töötaja kustutamine nimekirjast (DELETE)
    public void deleteEmployee(@PathVariable("id") int id) { //void, ei tagasta midagi
        employees.remove(id);
    }
    //localhost:8080/employee/0 - TÖÖTAB


    @PostMapping("test/{id1}/{id2}/{id3}")
    public String test (@PathVariable("id1") String pathId1,
                        @PathVariable (value = "id2", required = false) Long pathId2,
                        @PathVariable("id3") BigInteger pathId3,
                        @RequestBody Employee reqestBody, //Sadaame JSonit Bodys
                        @RequestParam(value = "id1", required = false) Integer queryId1,
                        @RequestParam(value = "id3") BigInteger queryId3){
        return "OK";
    }
    //localhost:8080/test/1/2/3?id1=12&id2=53&id3=234

    @GetMapping("fib")//Mõjutab veebiaadressit küsimärgini Nt. localhost:8080/fib?a=5
    public int fibonacci(@RequestParam("a") int a) { //selle rea a peale küsimärki
        Lesson2.fibonacci(a);
        return Lesson2.fibonacci(a);
        //localhost:8080/fib?a=12, anname ette numbri a millega arvutab fibonacci jada n elementi
    }

    @GetMapping("min")//Töötab
    public int min(@RequestParam("a") int a, @RequestParam("b") int b) {
        Lesson1MathUtil.min(a, b);
        return Lesson1MathUtil.min(a, b);
    }

    @GetMapping("max")//Töötab
    public int max(@RequestParam("a") int a, @RequestParam("b") int b) {
        Lesson1MathUtil.max(a, b);
        return Lesson1MathUtil.max(a, b);
    }

    @GetMapping("abs")//Töötab
    public int abs(@RequestParam("a") int a) {
        Lesson1MathUtil.abs(a);
        return Lesson1MathUtil.abs(a);
    }

    @GetMapping("iseven")//Töötab
    public boolean isEven(@RequestParam("a") int a) {
        Lesson1MathUtil.isEven(a);
        return Lesson1MathUtil.isEven(a);
    }

    @GetMapping("min2")//Töötab
    public int min2(@RequestParam("a") int a, @RequestParam("b") int b, @RequestParam("c") int c) {
        //localhost:8080/min2?a=11&b=22&c=33
        //Enne küsimärki GetMapping sulud, peale küsimärki RequestParam sisend
        //Eraldamine & märgiga
        Lesson1MathUtil.min2(a, b, c);
        return Lesson1MathUtil.min2(a, b, c);
    }

    @GetMapping("max2")//Töötab
    public int max2(@RequestParam("a") int a, @RequestParam("b") int b, @RequestParam("c") int c) {
        //localhost:8080/max2?a=10&b=12&c=14
        Lesson1MathUtil.max2(a, b, c);
        return Lesson1MathUtil.max2(a, b, c);
    }

    @GetMapping("exercise1")//vaja katsetada
    public int[] exercise1(@RequestParam("a") int[] m) { //siin int[] m, edaspidi m
        Lesson2.exercise1(m);
        return Lesson2.exercise1(m);
    }

    @GetMapping("exercise2")//Töötab
    public int[] exercise2(@RequestParam("a") int a) {
        Lesson2.exercise2(a);
        return Lesson2.exercise2(a);
    }

    @GetMapping("exercise5") //Töötab
    public int[] exercise5(@RequestParam("a") int a, @RequestParam("b") int b) {
        Lesson2.exercise5(a, b);
        return Lesson2.exercise5(a, b);
    }

    @GetMapping("exercise8")
    public BigInteger exercise8() {
        Lesson2.exercise8();
        return Lesson2.exercise8();
    }

    @GetMapping("dto")                          //brauseris peale / on dto
    public Employee dtoTest() {
        Employee employee = new Employee();     //loob uue employee
        employee.setAge(30);                    //seab töötaja vanuseks 30
        employee.setName("Taavi");              //seab töötaja nimeks Taavi
        return employee;                        //tagasta töötaja väärtus, töötaja on muutus
    }

    @PostMapping("test")
    public void posttest(@RequestBody Employee employee) {
        System.out.println(employee.getName());     //Prindib terminalis employee nime
        System.out.println(employee.getAge());      //Prindib terminalis töötaja vanuse
    }

}