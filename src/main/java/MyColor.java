import java.awt.*;
import java.awt.color.ColorSpace;

public class MyColor extends Color {

    public static final MyColor PRIMARY_COLOR = new MyColor(49, 67, 91);
    public static final MyColor SECONDARY_COLOR = new MyColor(174, 190, 206);
    public static final MyColor BUTTON_COLOR = new MyColor(3, 163, 164);

    public MyColor(int r, int g, int b) {
        super(r, g, b);
    }

    public MyColor(int r, int g, int b, int a) {
        super(r, g, b, a);
    }

    public MyColor(int rgb) {
        super(rgb);
    }

    public MyColor(int rgba, boolean hasalpha) {
        super(rgba, hasalpha);
    }

    public MyColor(float r, float g, float b) {
        super(r, g, b);
    }

    public MyColor(float r, float g, float b, float a) {
        super(r, g, b, a);
    }

    public MyColor(ColorSpace cspace, float[] components, float alpha) {
        super(cspace, components, alpha);
    }
}
