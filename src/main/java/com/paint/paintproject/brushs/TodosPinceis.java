package com.paint.paintproject.brushs;

public class TodosPinceis {
    /*
    private EventHandler<MouseEvent> mouseDraggedSimpleBrush(){
        return e -> {
            if (this.toolSelected && !this.bsize.getText().isEmpty()){
                brushTool.setLineCap(StrokeLineCap.ROUND);
                brushTool.setLineJoin(StrokeLineJoin.ROUND);
                brushTool.setLineWidth(Double.parseDouble(this.bsize.getText()));
                brushTool.setStroke(colorPicker.getValue());
                brushTool.lineTo(e.getX(), e.getY());
                brushTool.stroke();
            }
        };
    }
    private EventHandler<MouseEvent> mousePressedSimpleBrush(){
        return e -> {
            if (this.toolSelected && !this.bsize.getText().isEmpty()){
                brushTool.beginPath();
                brushTool.moveTo(e.getX(), e.getY());
                brushTool.stroke();
            }
        };
    }

    private EventHandler<MouseEvent> mouseDraggedShadowBrush(){
        return e -> {
            if (this.toolSelected && !this.bsize.getText().isEmpty()) {
                brushTool.lineTo(e.getX(), e.getY());
                brushTool.stroke();
            }
        };
    }
    private EventHandler<MouseEvent> mousePressedShadowBrush(){
        return e -> {
            if (this.toolSelected && !this.bsize.getText().isEmpty()) {
                brushTool.beginPath();
                brushTool.setLineCap(StrokeLineCap.ROUND);
                brushTool.setLineJoin(StrokeLineJoin.ROUND);
                brushTool.setLineWidth(Double.parseDouble(this.bsize.getText()));
                brushTool.setStroke(colorPicker.getValue());
                brushTool.setEffect(new Shadow(Double.parseDouble(this.bsize.getText()) / 2 , colorPicker.getValue()));
                brushTool.moveTo(e.getX(), e.getY());
            }
        };
    }
    private EventHandler<MouseEvent> mouseDraggedBezierBrush(){
        return e -> {
            if (this.toolSelected && !this.bsize.getText().isEmpty()){
                points.add(new Point((int) e.getX(), (int) e.getY()));
                brushTool.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                Point p1 = points.get(0);
                Point p2 = points.get(1);

                brushTool.beginPath();
                brushTool.moveTo(p1.x, p1.y);
                for(int i = 1; i < points.size() - 1; i++){
                    Point midPoint = midPintBtw(p1, p2);
                    brushTool.quadraticCurveTo(p1.x, p1.y, midPoint.x, midPoint.y);
                    p1 = points.get(i);
                    p2 = points.get(i + 1);
                }
                brushTool.setLineCap(StrokeLineCap.ROUND);
                brushTool.setLineJoin(StrokeLineJoin.ROUND);
                brushTool.setLineWidth(Double.parseDouble(this.bsize.getText()));
                brushTool.setStroke(colorPicker.getValue());
                brushTool.lineTo(p1.x,p1.y);
                brushTool.stroke();
            }
        };
    }
    private EventHandler<MouseEvent> mousePressedBezierBrush(){
        return e ->{
            if (this.toolSelected && !this.bsize.getText().isEmpty()){
                points.add(new Point((int) e.getX(), (int) e.getY()));
                brushTool.moveTo(e.getX(), e.getY());
                brushTool.stroke();
            }
        };
    }
    private EventHandler<MouseEvent> mouseDraggedImg(){
        return e -> {
            if (this.toolSelected && !this.bsize.getText().isEmpty()){
                loadImage(Double.parseDouble(this.bsize.getText()));
                brushTool.beginPath();
                Point currentPoint = new Point();
                currentPoint.x = (int) e.getX(); currentPoint.y = (int) e.getY();
                double dist = distanceBetweenPoints(lastPoint, currentPoint);
                double angle = angleBetweenPoints(lastPoint, currentPoint);
                Point pointToDraw = new Point();
                for(int i = 0; i < dist; i++){
                    pointToDraw.x = (int) (lastPoint.x + (Math.sin(angle) * i) - 25);
                    pointToDraw.y = (int) (lastPoint.y + (Math.sin(angle) * i) - 25);
                    brushTool.drawImage(image, pointToDraw.x, pointToDraw.y);
                }
                lastPoint = currentPoint;
            }
        };
    }
    private EventHandler<MouseEvent> mousePressedImg(){
        return e -> {
            if (this.toolSelected && !this.bsize.getText().isEmpty()){
                lastPoint = new Point((int) e.getX(), (int)e.getY());
            }
        };
    }
    private EventHandler<MouseEvent> mouseDraggedRotateImg(){
        return e -> {
            if (this.toolSelected && !this.bsize.getText().isEmpty()){
                brushTool.beginPath();
                loadImage(Double.parseDouble(this.bsize.getText()));
                Point currentPoint = new Point((int) e.getX(), (int) e.getY());

                double dist = distanceBetweenPoints(lastPoint, currentPoint);
                double angle = angleBetweenPoints(lastPoint, currentPoint);

                brushTool.setLineCap(StrokeLineCap.ROUND);
                brushTool.setLineJoin(StrokeLineJoin.ROUND);

                for(int i = 0; i < dist; i++){
                    double x = lastPoint.x + (Math.sin(angle) * i);
                    double y = lastPoint.y + (Math.sin(angle) * i);
                    brushTool.save();
                    brushTool.translate(x, y);
                    brushTool.scale(1, 1);
                    brushTool.rotate(Math.PI * 180 / (int) getRandomValue(0, 180));
                    brushTool.drawImage(image, 0, 0);
                    brushTool.restore();
                }
                lastPoint = currentPoint;
            }
        };
    }
    private EventHandler<MouseEvent> mousePressedRotateImg(){
        return e -> {
            if (this.toolSelected && !this.bsize.getText().isEmpty()){
                lastPoint = new Point((int) e.getX(), (int)e.getY());
            }
        };
    }
    private EventHandler<MouseEvent> mouseDraggedPen(){
        return e -> {
            if (this.toolSelected && !this.bsize.getText().isEmpty()){
                brushTool.setLineCap(StrokeLineCap.ROUND);
                brushTool.setLineJoin(StrokeLineJoin.ROUND);
                brushTool.setStroke(colorPicker.getValue());

                pontos.add(new Ponto(new Point((int) e.getX(), (int) e.getY()),  (int) getRandomValue(3, 5)));

                for (int i = 1; i < pontos.size(); i++){
                    brushTool.beginPath();
                    brushTool.moveTo(pontos.get(i-1).getPoint().getX(), pontos.get(i-1).getPoint().getY());
                    brushTool.setLineWidth(pontos.get(i).getWidth());
                    brushTool.lineTo(pontos.get(i).getPoint().getX(), pontos.get(i).getPoint().getY());
                    brushTool.stroke();
                }
            }
        };
    }
    private EventHandler<MouseEvent> mousePressedPen(){
        return e -> {
            if (this.toolSelected && !this.bsize.getText().isEmpty()){
                pontos.add(new Ponto(new Point((int) e.getX(), (int) e.getY()),  (int) getRandomValue(3, 5)));
            }
        };
    }
    private EventHandler<MouseEvent> mouseDraggedblurredPen(){
        return e -> {
            if (this.toolSelected && !this.bsize.getText().isEmpty()){
                //brushTool.setLineCap(StrokeLineCap.ROUND);
                //brushTool.setLineJoin(StrokeLineJoin.ROUND);
                double size = Double.parseDouble(this.bsize.getText());

                brushTool.setStroke(colorPicker.getValue());
                brushTool.setLineWidth(size);

                brushTool.beginPath();
                brushTool.moveTo(lastPoint.getX() - (int) getRandomValue(0, (int) (size * 2)), lastPoint.getY() - (int) getRandomValue(0,(int) (size * 2) ));
                brushTool.lineTo(e.getX() - (int) getRandomValue(0, (int) (size * 2)), e.getY() - (int) getRandomValue(0, (int) (size * 2)));
                brushTool.stroke();

                brushTool.moveTo(lastPoint.getX(), lastPoint.getY());
                brushTool.lineTo(e.getX(), e.getY());
                brushTool.stroke();

                brushTool.moveTo(lastPoint.getX() + (int) getRandomValue(0, (int) (size * 2)), lastPoint.getY() + (int) getRandomValue(0, (int) (size * 2)));
                brushTool.lineTo(e.getX() + (int) getRandomValue(0, (int) (size * 2)), e.getY() + (int) getRandomValue(0, (int) (size * 2)));
                brushTool.stroke();

                lastPoint = new Point((int) e.getX(), (int) e.getY());
            }
        };
    }
    private EventHandler<MouseEvent> mousePressedblurredlePen(){
        return e -> {
            lastPoint = new Point((int) e.getX(), (int) e.getY());
        };
    }
    private EventHandler<MouseEvent> mouseDraggedMultiplePen(){
        return e -> {
            if (this.toolSelected && !this.bsize.getText().isEmpty()){
                brushTool.setLineCap(StrokeLineCap.ROUND);
                brushTool.setLineJoin(StrokeLineJoin.ROUND);
                brushTool.setStroke(colorPicker.getValue());
                brushTool.setLineWidth(Double.parseDouble(this.bsize.getText()));

                brushTool.beginPath();
                brushTool.moveTo(lastPoint.getX() - (int) getRandomValue(0, 2), lastPoint.getY() - (int) getRandomValue(0, 2));
                brushTool.lineTo(e.getX() - (int) getRandomValue(0, 2), e.getY() - (int) getRandomValue(0, 2));
                brushTool.stroke();

                brushTool.moveTo(lastPoint.getX(), lastPoint.getY());
                brushTool.lineTo(e.getX(), e.getY());
                brushTool.stroke();

                brushTool.moveTo(lastPoint.getX() + (int) getRandomValue(0, 2), lastPoint.getY() + (int) getRandomValue(0, 2));
                brushTool.lineTo(e.getX() + (int) getRandomValue(0, 2), e.getY() + (int) getRandomValue(0, 2));
                brushTool.stroke();

                lastPoint = new Point((int) e.getX(), (int) e.getY());
            }
        };
    }
    private EventHandler<MouseEvent> mousePressedMultiplePen(){
        return e -> {
            if (this.toolSelected && !this.bsize.getText().isEmpty()) {
                lastPoint = new Point((int) e.getX(), (int) e.getY());
            }
        };
    }
    private EventHandler<MouseEvent> mouseDraggedSlicedWithOpacity(){
        return e -> {
            if (this.toolSelected && !this.bsize.getText().isEmpty()) {
                brushTool.beginPath();

                brushTool.setLineCap(StrokeLineCap.ROUND);
                brushTool.setLineJoin(StrokeLineJoin.ROUND);
                brushTool.setStroke(colorPicker.getValue());

                brushTool.setGlobalAlpha(1);
                brushTool.moveTo(lastPoint.x - 4, lastPoint.y - 4);
                brushTool.lineTo(e.getX() - 4, e.getY() - 4);
                brushTool.stroke();

                brushTool.setGlobalAlpha(0.6);
                brushTool.moveTo(lastPoint.x - 2, lastPoint.y - 2);
                brushTool.lineTo(e.getX() - 2, e.getY() - 2);
                brushTool.stroke();

                brushTool.setGlobalAlpha(0.4);
                brushTool.moveTo(lastPoint.x, lastPoint.y);
                brushTool.lineTo(e.getX(), e.getY());
                brushTool.stroke();

                brushTool.setGlobalAlpha(0.3);
                brushTool.moveTo(lastPoint.x + 2, lastPoint.y + 2);
                brushTool.lineTo(e.getX() + 2, e.getY() + 2);
                brushTool.stroke();

                brushTool.setGlobalAlpha(0.2);
                brushTool.moveTo(lastPoint.x + 4, lastPoint.y + 4);
                brushTool.lineTo(e.getX() + 4, e.getY() + 4);
                brushTool.stroke();

                lastPoint = new Point((int) e.getX(), (int) e.getY());
            }
        };
    }
    private EventHandler<MouseEvent> mousePressedSlicedWithOpacity(){
        return e -> {
            if (this.toolSelected && !this.bsize.getText().isEmpty()) {
                lastPoint = new Point((int) e.getX(), (int) e.getY());
            }
        };
    }
    private EventHandler<MouseEvent> mouseDraggedConnectionPoints(){
        return e -> {
            if (this.toolSelected && !this.bsize.getText().isEmpty()) {
                brushTool.setLineCap(StrokeLineCap.ROUND);
                brushTool.setLineJoin(StrokeLineJoin.ROUND);
                brushTool.setStroke(colorPicker.getValue());
                brushTool.setLineWidth(1);
                points.add(new Point((int) e.getX(), (int) e.getY()));
                brushTool.beginPath();
                brushTool.moveTo(points.get(0).getX(), points.get(0).getY());
                for(int i = 1; i < points.size(); i++){
                    brushTool.lineTo(points.get(i).getX(), points.get(i).getY());
                    Point nearPoint = (i - 5) <= 0 ? null : points.get(i - 5);
                    if(Objects.nonNull(nearPoint)){
                        brushTool.moveTo(nearPoint.getX(), nearPoint.getY());
                        brushTool.lineTo(points.get(i).getX(), points.get(i).getY());
                    }
                }
                brushTool.stroke();
            }
        };
    }
    private EventHandler<MouseEvent> mousePressedConnectionPoints(){
        return e -> {
            if (this.toolSelected && !this.bsize.getText().isEmpty()) {
                points.add(new Point((int) e.getX(), (int) e.getY()));
            }
        };
    }
    private EventHandler<MouseEvent> mouseDraggedNeighborPoints(){
        return e -> {
            if (this.toolSelected && !this.bsize.getText().isEmpty()) {
                points.add(new Point((int) e.getX(), (int) e.getY()));
                brushTool.setLineCap(StrokeLineCap.ROUND);
                brushTool.setLineJoin(StrokeLineJoin.ROUND);
                brushTool.beginPath();
                brushTool.moveTo(points.get(points.size() - 2).getX(), points.get(points.size() - 2).getY());
                brushTool.lineTo(points.get(points.size() - 1).getX(), points.get(points.size() - 1).getY());
                brushTool.stroke();
                Point lastPoint = points.get(points.size() - 1);
                for (int i = 0; i < points.size(); i++) {
                    double dx = points.get(i).getX() - points.get(points.size() - 1).getX();
                    double dy = points.get(i).getY() - points.get(points.size() - 1).getY();
                    double d = (dx * dx) + (dy * dy);
                    if (d < 1000) {
                        brushTool.beginPath();
                        //brushTool.setStroke(Color.rgb(40, 45, 45, 0.3));
                        brushTool.setStroke(compositeColor(colorPicker.getValue(), 0.3));
                        brushTool.moveTo(lastPoint.x + (dx * 0.2), lastPoint.y + (dy * 0.2));
                        brushTool.lineTo(points.get(i).x - (dx * 0.2), points.get(i).y - (dy * 0.2));
                        brushTool.stroke();
                    }
                }
            }
        };
    }
    private EventHandler<MouseEvent> mousePressedNeighborPoints(){
        return e -> {
            if (this.toolSelected && !this.bsize.getText().isEmpty()) {
                points.add(new Point((int) e.getX(), (int) e.getY()));
            }
        };
    }
    private EventHandler<MouseEvent> mouseFurDraggedNeighborPoints(){
        return e -> {
            if (this.toolSelected && !this.bsize.getText().isEmpty()) {
                points.add(new Point((int) e.getX(), (int) e.getY()));
                brushTool.setLineCap(StrokeLineCap.ROUND);
                brushTool.setLineJoin(StrokeLineJoin.ROUND);
                brushTool.beginPath();
                brushTool.moveTo(points.get(points.size() - 2).getX(), points.get(points.size() - 2).getY());
                brushTool.lineTo(points.get(points.size() - 1).getX(), points.get(points.size() - 1).getY());
                brushTool.stroke();
                for (int i = 0; i < points.size(); i++) {
                    double dx = points.get(i).getX() - points.get(points.size() - 1).getX();
                    double dy = points.get(i).getY() - points.get(points.size() - 1).getY();
                    double d = (dx * dx) + (dy * dy);
                    if (d < 2000 && Math.random() > d / 2000) {
                        brushTool.beginPath();
                        //brushTool.setStroke(Color.rgb(40, 45, 45, 0.3));
                        brushTool.setStroke(compositeColor(colorPicker.getValue(), 0.3));
                        brushTool.moveTo(points.get(points.size() - 1).getX() + (dx * 0.5), points.get(points.size() - 1).getY() + (dy * 0.5));
                        brushTool.lineTo(points.get(points.size() - 1).getX() - (dx * 0.5), points.get(points.size() - 1).getY() - (dy * 0.5));
                        brushTool.stroke();

                    }
                }
            }
        };
    }
    private EventHandler<MouseEvent> mouseFurPressedNeighborPoints(){
        return e -> {
            if (this.toolSelected && !this.bsize.getText().isEmpty()) {
                points.add(new Point((int) e.getX(), (int) e.getY()));
            }
        };
    }


private Point midPintBtw(Point point1, Point point2){
        Point midPoint = new Point();
        midPoint.x = point1.x + (point2.x - point1.x) / 2;
        midPoint.y = point1.y + (point2.y - point1.y) / 2;
        return midPoint;
    }
    private double distanceBetweenPoints(Point point1, Point point2){
        return Math.sqrt(Math.pow(point2.x - point1.x, 2) + Math.pow(point2.y - point1.y, 2));
    }
    private double angleBetweenPoints(Point point1, Point point2){
        return Math.atan2( point2.x - point1.x, point2.y - point1.y );
    }
    private double getRandomValue(int min, int max){
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }
    private void loadImage(double size){
        try {
            InputStream stream = new FileInputStream("E:\\workspace\\paint-project\\images\\brush2.png");
            image = new Image(stream, size, size, false, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private Color compositeColor(Color color, double opacity){
        Color color1 = new Color(color.getRed(), color.getGreen(), color.getBlue(), opacity);
        return color1;
    }


private VBox createListOfBrushWithImage(){
        VBox vb = new VBox(listWithBrushAndImage());
        return vb;
    }

    private HBox listWithBrushAndImage(){
        HBox hb = new HBox(listViewOfBrushs(130),listViewOfBrushs(220));
        return hb;
    }

    private ListView<Brush> listViewOfBrushs(int width){
        ListView<Brush> lv = new ListView<>();
        lv.setPrefWidth(width);
        lv.setPrefHeight(655);
        lv.setItems(observableListOfBrushs());
        return lv;
    }

    private ObservableList<Brush> observableListOfBrushs(){
        ObservableList<Brush> ol = FXCollections.observableList(listOfBrush());
        return  ol;
    }

    private List<Brush> listOfBrush(){
        List<Brush> listBrush = new ArrayList<>();
        listBrush.add(new Brush());
        return listBrush;
    }



























    */
}
