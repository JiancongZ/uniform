package problem;
import java.lang.*;
import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Point2D.Double;

import tester.Tester;

public class RapidTree {
	
	ArrayList<Node> tree = new ArrayList<Node>();
	Point2D source = new Point2D.Double();
	Point2D target = new Point2D.Double();
	Node root = new Node(source);
	int maxPoints = 1000000;
	
	public RapidTree(Point2D source,Point2D target){	
		this.source =source;
		this.root = new Node(source);
		this.target= target;
		tree.add(new Node(source));
	}
	
	public double distance (Point2D x, Point2D y){
		return Math.sqrt((x.getX()-y.getX())*((x.getX()-y.getX()))+((x.getY()-y.getY()))*(x.getY()-y.getY()));
	}
	
	/*public Node nearest(Node root, double shortest, Point2D dest){
		Node node = root;
		//double shortest = 1.0;
		
		for (Node n : root.getChild()){
		
			if (distance(n.getNode(),dest)<shortest){
				shortest = distance(n.getNode(),dest);
				node = n;
				node = nearest(n,shortest,dest);
			}else{
				//node = nearest(n,shortest,dest);//? unnecessary
			}
		}
		return node;
	}*/
	
	public Node nearest (ArrayList<Node> tree, Point2D dest){
		Node temp=root;
		ArrayList<Node> ttree = tree;
		double shortest=0.99;
		
		for (int i = 0 ;i<ttree.size();i++){
			if (distance(tree.get(i).getNode(),dest)<shortest){
				shortest = distance(tree.get(i).getNode(),dest);
				temp = tree.get(i);
				
			}
				
		}
		
		return temp;
		
	}
	
	public Node construct(ArrayList<Obstacle> obstacles){
		
		int num=0;
		Node dest = null;
		
		while(num<maxPoints){
			
			double rx,ry;
			do{
				rx = Math.random();
				ry = Math.random();
			}while(rx==0 || ry ==0);
			
			Point2D rand = new Point2D.Double(rx, ry);
		
			Node nearest = nearest(tree,rand);//root.closest_vertex(0.99, rand);

			double line = 0.001/distance(nearest.getNode(),rand);
			
			double x = nearest.getX()+(rand.getX()-nearest.getX())*line;
			double y = nearest.getY()+(rand.getY()-nearest.getY())*line;
		
			Node n = new Node(new Point2D.Double(x,y));
			
			boolean collision = false;
		
			for (Obstacle o : obstacles){
				if (o.getRect().contains(n.getNode()))
					collision = true;
				else 
					if (new Line2D.Double(nearest.getNode(),n.getNode()).intersects(o.getRect()))
						collision = true;
			
			}
		
			if (!Tester.BOUNDS.contains(n.getNode()))
				collision = true;
			
			if (!collision){
				num++;
				//System.out.println(nearest.getNode().toString());
				tree.add(n);
				n.father=nearest;
				nearest.child.add(n);
				
				if (distance(n.getNode(),target)<0.01){
					
					//System.out.println("final:"+n.getNode().toString());
					dest = n;
					break;
				}
					
				//System.out.println(num+";"+nearest.getNode().toString()+";"+nearest.getChild().size());
			}
		}
		
		return dest;
	} 
	
	public void setChild(Node n, Point2D p){
		n.setChild(new Node(p));
	}

}
