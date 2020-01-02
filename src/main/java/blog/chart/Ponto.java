package blog.chart;

public class Ponto {
    private Integer x;
    private Integer y;
    private String indexLabel;

    public Ponto(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Ponto(Integer x, Integer y, String indexLabel) {
        this.x = x;
        this.y = y;
        this.indexLabel = indexLabel;
    }

    public Integer getX() {
        return this.x;
    }
    public void setX(Integer x) {
        this.x = x;
    }
    public Integer getY() {
        return this.y;
    }
    public void setY(Integer y) {
        this.y = y;
    }
}
