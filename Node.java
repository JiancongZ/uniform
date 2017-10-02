package problem;

import java.awt.geom.Point2D;
import java.util.*;

class Node{
	private Point2D nodeValue;
	ArrayList<Node> child = new ArrayList<Node>();
	private int branch;
	Node father = null;
	double h;
	double g;
	//private Point2D right;
	//private int branch;
	
	public Node(Point2D p){
		this.nodeValue=p;
		//this.branch=b;
		//child = new Node[b];
	}
	
	public double getX(){
		return this.nodeValue.getX();
	}
	
	public double getY(){
		return this.nodeValue.getY();
	}
	
	public Point2D getNode(){
		return nodeValue;
	}
	
	public ArrayList<Node> getChild(){
		return child;
	}
	
	public void setChildren(ArrayList<Node> child){
		this.child=child;
	}
	
	public boolean setChild(Node child){
		if (this.getChild().size() >= branch)
			return false;
		
		this.getChild().add(child);
		return true;
		
	}
	
	public double distance (Point2D x, Point2D y){
		return Math.sqrt((x.getX()-y.getX())*((x.getX()-y.getX()))+((x.getY()-y.getY()))*(x.getY()-y.getY()));
	}
	
	public Node closest_vertex(double shortest_distance, Point2D a)
    {
        Node closest_vertex = this;
        for (Node aChildren : this.child) {
            if (distance(aChildren.nodeValue, a) < shortest_distance) {
                shortest_distance = distance(aChildren.nodeValue, a);
                closest_vertex = aChildren.closest_vertex(shortest_distance, a);
            } else {
                aChildren.closest_vertex(shortest_distance, a);
            }
        }
        return closest_vertex;
    }
	
}