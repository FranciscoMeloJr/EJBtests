public class Recur {
public static void main(String[] argv) {
    recur();
}
static void recur() {
    Object[] o = null;
    try {
        while(true) {
            Object[] newO = new Object[1];
            newO[0] = o;
            o = newO;
        }
    }
    finally {
        recur();
    }
}
}
