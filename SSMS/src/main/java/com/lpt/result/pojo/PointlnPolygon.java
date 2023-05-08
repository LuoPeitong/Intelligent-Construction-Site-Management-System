package com.lpt.result.pojo;

import java.util.ArrayList;
import java.util.List;

public class PointlnPolygon {
    // 判断点是否在多边形内
    public static boolean isPointInPolygon(Point point, Point[] polygon) {
        int num = polygon.length;
        int i, j;
        boolean inside = false;

        for (i = 0, j = num - 1; i < num; j = i++) {
            if (((polygon[i].y > point.y) != (polygon[j].y > point.y)) &&
                    (point.x < (polygon[j].x - polygon[i].x) * (point.y - polygon[i].y) /
                            (polygon[j].y - polygon[i].y) + polygon[i].x)) {
                inside = !inside;
            }
        }

        return inside;
    }

    // 点类
    static class Point {
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
    public static boolean isPointInArea(double x, double y, String points) {
        // 将点串按照分号分隔成一个字符串数组
        String[] strPoints = points.split(";");

        // 构造 Point 对象列表
        List<Point> pointList = new ArrayList<>();
        for (String strPoint : strPoints) {
            String[] coords = strPoint.split(",");
            if (coords.length == 2) {
                double lon = Double.parseDouble(coords[0]);
                double lat = Double.parseDouble(coords[1]);
                pointList.add(new Point(lon, lat));
            }
        }

        // 将 Point 对象列表转换为 Point[] 数组
        Point[] polygon = pointList.toArray(new Point[pointList.size()]);

        // 判断点是否在多边形内
        return isPointInPolygon(new Point(x, y), polygon);
    }
}
