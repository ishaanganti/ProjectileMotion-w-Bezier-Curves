import java.awt.*;
import java.util.*;
import java.awt.geom.*;
import java.awt.event.*;

//VELOCITY IS A CONSTANT 10
//Calculations for x and y coordinates done by myself ;) by combining x-pos vs t and y-pos vs t.

public class ProjectileMotion {
	public static void main(String[] args) {

		//Prompting for start angle, final angle, and interval
		Scanner console = new Scanner(System.in);
		System.out.print("Please enter the start angle in degrees: ");
		double angleDegrees = console.nextDouble();
		double angle = Math.toRadians(angleDegrees); 
		System.out.print("Please enter the final angle in degrees: ");
		double final_angle_Degrees = console.nextDouble();
		double final_angle = Math.toRadians(final_angle_Degrees); 
		System.out.print("Please enter the step size in degrees: ");
		int step_size = console.nextInt(); 
		double angle_counter = angle;
	


		//setting up drawing materials
		DrawingPanel panel = new DrawingPanel(1000,700);
		Graphics2D g = panel.getGraphics();
		QuadCurve2D q = new QuadCurve2D.Float();

		//creating axes
		g.drawLine(50,650, 950, 650);
		g.drawLine(50,650, 50, 50);


		//creating trajectories models on canvas
		for(int i = (int) angleDegrees; i<= (int) final_angle_Degrees; i+= step_size) {
			q.setCurve(50, 650,80*VertexXCoord(angle_counter)+50, 650-160*VertexYCoord(angle_counter), 80*EndXCoord(angle_counter)+50, 650);
			g.draw(q);
			angle_counter = Math.toDegrees(angle_counter);
			angle_counter+= step_size;
			angle_counter = Math.toRadians(angle_counter);

			//change color of every line
			g.setColor(new Color((18*i)%255, (35*i)%255, (7*i)%255));
		}


		//making ticks along axes
		g.setColor(new Color(0,0,0));
		String numberString;
		String numberString2;
		for(int i = 1; i <= 11; i++){
			g.drawLine(130 + 80*(i-1), 645, 130 + 80*(i-1), 655);
			numberString = "" + i;
			g.drawString(numberString, 130 + 80*(i-1), 670);
		}

		for(int i = 1; i <= 7; i++){
			g.drawLine(45, 650 - 80*i, 55, 650 - 80*i);
			numberString2 = "" + i;
			g.drawString(numberString2, 25,650 - 80*i );
		}

		//drawing title
		g.drawString("Projectile Motion at 10 m/s", 400, 100);
	}

//x-coordinate of the ending point
	public static double EndXCoord(double inclineAngle) {
		double horizontal_distance = 400*Math.cos(inclineAngle)*Math.cos(inclineAngle)*Math.tan(inclineAngle)/19.62;
		return horizontal_distance;
	}


//x-coordinate of the vertex
	public static double VertexXCoord(double inclineAngle) {
		double half_horizontal_distance = EndXCoord(inclineAngle)/2;
		return half_horizontal_distance;
	}


//y-coordinate of the vertex
	public static double VertexYCoord(double inclineAngle) {
		double equation_p1 = Math.tan(inclineAngle)*VertexXCoord(inclineAngle);
		double equation_num_p2 = -9.81*VertexXCoord(inclineAngle)*VertexXCoord(inclineAngle);
		double equation_den_p2 = 2*Math.pow(10*Math.cos(inclineAngle), 2);
		double max_height = equation_p1 + (equation_num_p2/equation_den_p2);
		return max_height;
	}

}