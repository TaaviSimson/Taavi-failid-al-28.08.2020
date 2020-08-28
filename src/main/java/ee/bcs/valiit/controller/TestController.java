package ee.bcs.valiit.controller;

import ee.bcs.valiit.Lesson1MathUtil;
import ee.bcs.valiit.Lesson2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("fib")//Mõjutab veebiaadressit küsimärgini Nt. localhost:8080/fib?a=5
    public int fibonacci(@RequestParam("a") int a) { //selle rea a peale küsimärki
        Lesson2.fibonacci(a);
        return Lesson2.fibonacci(a);
        //localhost:8080/fib?a=12, anname ette numbri a millega arvutab fibonacci jada n elementi
    }

    @GetMapping("min")//Töötab
    public int min(@RequestParam("a") int a, @RequestParam("b") int b){
        Lesson1MathUtil.min(a,b);
        return Lesson1MathUtil.min(a,b);
    }

    @GetMapping("max")//Töötab
    public int max(@RequestParam("a") int a, @RequestParam("b") int b) {
        Lesson1MathUtil.max(a, b);
        return Lesson1MathUtil.max(a, b);
    }

    @GetMapping("abs")//Töötab
    public int abs(@RequestParam("a") int a){
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
        Lesson1MathUtil.min2(a,b,c);
        return Lesson1MathUtil.min2(a,b,c);
    }

    @GetMapping("max2")//Töötab
    public int max2(@RequestParam("a") int a, @RequestParam("b") int b, @RequestParam("c") int c) {
        //localhost:8080/max2?a=10&b=12&c=14
        Lesson1MathUtil.max2(a, b, c);
        return Lesson1MathUtil.max2(a, b, c);
    }

    @GetMapping("exercise1")//vaja katsetada
    public int[] exercise1(@RequestParam("a") int a){
        Lesson2.exercise1();
        return Lesson2.exercise1();
    }

    @GetMapping("exercise2")//Töötab
    public int[] exercise2(@RequestParam("a") int a){
        Lesson2.exercise2(a);
        return Lesson2.exercise2(a);
    }

    @GetMapping("exercise5") //Töötab
    public int [] exercise5(@RequestParam("a") int a, @RequestParam("b") int b){
        Lesson2.exercise5(a,b);
        return Lesson2.exercise5(a,b);
    }


}