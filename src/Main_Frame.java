public class Main_Frame {
    public Double formula1(Double x, Double y, Double z) {

        return Math.sin(Math.log(y) + Math.sin(Math.PI)*y*y) * Math.pow(x*x+Math.sin(z)+Math.pow(Math.E, Math.cos(z)), 1 / 4);
    }

    public Double formula2(Double x, Double y, Double z) {
        return Math.pow(Math.cos(Math.pow(Math.E,x)) + Math.log((1+y)*(1+y)) + Math.pow((Math.pow(Math.E,Math.cos(x))+Math.pow(Math.sin(Math.PI),2)*z),1/2)
                +Math.pow(1/x,1/2)+Math.cos(Math.pow(y,2)),Math.sin(z));
    }

}

