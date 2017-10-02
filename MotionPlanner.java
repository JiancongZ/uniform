package problem;

import tester.Tester;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.io.IOException;
import java.lang.*;
import java.util.*;
import java.io.*;

public class MotionPlanner {
	
	private static Tester tester = new Tester();
	
	/*
	 * asv1 is destination, find a path from asv2 to asv1
	 */
	public static ArrayList<ASVConfig> merge(ASVConfig asv1, ASVConfig asv2, ArrayList<Obstacle> obstacles){
		ArrayList<ASVConfig> list = new ArrayList<ASVConfig>();
		ASVConfig asv3 = asv2;
		int num = asv1.getASVCount();
		double coords[] = new double[2*num];
		boolean flag = true;
		boolean iflag = true;
		int mergeNum = 0;
		int j=0;
		
		while(mergeNum<num){
			
			flag = true;
			
			while (flag){
				
				if (mergeNum<1){
				
				
				

				
						if (asv1.getPosition(mergeNum).distance(asv3.getPosition(mergeNum))>0.001){
						coords[2*mergeNum]=(0.001/asv1.getPosition(mergeNum).distance(asv3.getPosition(mergeNum)))*(asv1.getPosition(mergeNum).getX()-asv3.getPosition(mergeNum).getX())+asv3.getPosition(mergeNum).getX();
						coords[2*mergeNum+1]=(0.001/asv1.getPosition(mergeNum).distance(asv3.getPosition(mergeNum)))*(asv1.getPosition(mergeNum).getY()-asv3.getPosition(mergeNum).getY())+asv3.getPosition(mergeNum).getY();
					
						for (int i =0;i<mergeNum;i++){
							coords[2*i] = asv1.getPosition(i).getX();
							coords[2*i+1] = asv1.getPosition(i).getY();
						}
			
						for (int i=mergeNum+1;i<num;i++){
							coords[2*i]=coords[2*i-2]-asv3.getPosition(i-1).getX()+asv3.getPosition(i).getX();
							coords[2*i+1]=coords[2*i-1]-asv3.getPosition(i-1).getY()+asv3.getPosition(i).getY();
				
						}
					
					}
				
					else{
						coords[2*mergeNum]=asv1.getPosition(mergeNum).getX();
						coords[2*mergeNum+1]=asv1.getPosition(mergeNum).getY();
						mergeNum ++;
					
						for (int i =0;i<mergeNum-1;i++){
							coords[2*i] = asv1.getPosition(i).getX();
							coords[2*i+1] = asv1.getPosition(i).getY();
						}
			
						for (int i=mergeNum;i<num;i++){
							coords[2*i]=coords[2*i-2]-asv3.getPosition(i-1).getX()+asv3.getPosition(i).getX();
							coords[2*i+1]=coords[2*i-1]-asv3.getPosition(i-1).getY()+asv3.getPosition(i).getY();
						}
					
						flag = false;
					}
					
			
					
				}// end if mergeNum<1
				/*
				 * 
				 */
				else{
					if (asv1.getPosition(mergeNum).distance(asv3.getPosition(mergeNum))>0.001){
						
						double x = asv3.getPosition(mergeNum).getX();
						double y = asv3.getPosition(mergeNum).getY();
						
						double xo = coords[2*mergeNum-2];
						double yo=coords[2*mergeNum-1];
						
						double xnew = asv1.getPosition(mergeNum).getX();
						double ynew = asv1.getPosition(mergeNum).getY();
						
						double div =  Math.floor(Math.sqrt((x-xnew)*(x-xnew)+(y-ynew)*(y-ynew))/0.001);
						
						coords[2*mergeNum]=(0.001/asv1.getPosition(mergeNum).distance(asv3.getPosition(mergeNum)))*(asv1.getPosition(mergeNum).getX()-asv3.getPosition(mergeNum).getX())+asv3.getPosition(mergeNum).getX();
						coords[2*mergeNum+1]=(0.001/asv1.getPosition(mergeNum).distance(asv3.getPosition(mergeNum)))*(asv1.getPosition(mergeNum).getY()-asv3.getPosition(mergeNum).getY())+asv3.getPosition(mergeNum).getY();
					
						
						//coords[2*mergeNum]=(1/div)*(asv1.getPosition(mergeNum).getX()-asv3.getPosition(mergeNum).getX())+asv3.getPosition(mergeNum).getX();
						//coords[2*mergeNum+1]=(1/div)*(asv1.getPosition(mergeNum).getY()-asv3.getPosition(mergeNum).getY())+asv3.getPosition(mergeNum).getY();
					
						if (coords[2*mergeNum]==asv1.getPosition(mergeNum).getX()){
							coords[2*mergeNum]=coords[2*mergeNum]+0.0005;
						}
							
							
						if (coords[2*mergeNum+1] == asv1.getPosition(mergeNum).getY()){
							coords[2*mergeNum+1]=coords[2*mergeNum+1]+0.0005;
						}
						
				
						
						for (int i =0;i<mergeNum;i++){
							coords[2*i] = asv1.getPosition(i).getX();
							coords[2*i+1] = asv1.getPosition(i).getY();
						}
			
						for (int i=mergeNum+1;i<num;i++){
							coords[2*i]=coords[2*i-2]-asv3.getPosition(i-1).getX()+asv3.getPosition(i).getX();
							coords[2*i+1]=coords[2*i-1]-asv3.getPosition(i-1).getY()+asv3.getPosition(i).getY();
				
						}
					
					}
					else{
						coords[2*mergeNum]=asv1.getPosition(mergeNum).getX();
						coords[2*mergeNum+1]=asv1.getPosition(mergeNum).getY();
						mergeNum ++;
					
						for (int i =0;i<mergeNum-1;i++){
							coords[2*i] = asv1.getPosition(i).getX();
							coords[2*i+1] = asv1.getPosition(i).getY();
						}
			
						for (int i=mergeNum;i<num;i++){
							coords[2*i]=coords[2*i-2]-asv3.getPosition(i-1).getX()+asv3.getPosition(i).getX();
							coords[2*i+1]=coords[2*i-1]-asv3.getPosition(i-1).getY()+asv3.getPosition(i).getY();
						}
					
						flag = false;
					}
				}
				
				System.out.println(new ASVConfig(coords));		
				list.add(new ASVConfig(coords));
				asv3 = new ASVConfig(coords);
				
			}
		}
		
		return list;
	}
	
	
	public static ArrayList<ASVConfig> makePath(ArrayList<Node> list, ASVConfig source, ASVConfig dest, ArrayList<Obstacle> obstacles){
		
		
		ArrayList<ASVConfig> solution = new ArrayList<ASVConfig>();
		solution.add(source);
		int count = source.getASVCount();
		double[] coords = new double[2*count];
		ASVConfig tmp;
		
		for (int i=1;i<list.size();i++){
			
			coords[0]=list.get(i).getX();
			coords[1]=list.get(i).getY();
			
			for (int j=1; j<count;j++){
				coords[2*j]=solution.get(solution.size()-1).getASVPositions().get(j).getX()+list.get(i).getNode().getX()-list.get(i-1).getNode().getX();
				coords[2*j+1]=solution.get(solution.size()-1).getASVPositions().get(j).getY()+list.get(i).getNode().getY()-list.get(i-1).getNode().getY();
			}
			
			tmp = new ASVConfig(coords);
			
			//begin to check
			
			if (tester.hasCollision(tmp, obstacles)){
				
				boolean xdirection = true;
				boolean ydirection = true;
			
				if (source.getPosition(0).getX()> tmp.getPosition(0).getX())
					xdirection = false;
				if (source.getPosition(0).getY()> tmp.getPosition(0).getY())
					ydirection = false;
			
				if (xdirection)
					for (int m =0;m<count;m++){
						coords[2*m]=tmp.getPosition(0).getX()-0.05*m;
						coords[2*m+1]=tmp.getPosition(0).getY();
					}
				else
					for (int m =0;m<count;m++){
						coords[2*m]=tmp.getPosition(0).getX()+0.05*m;
						coords[2*m+1]=tmp.getPosition(0).getY();
					}
					
					
				if (!tester.hasCollision(new ASVConfig(coords),obstacles)){
						System.out.println("xxx");
						solution.addAll(merge(new ASVConfig(coords),solution.get(solution.size()-1),obstacles));
				}
				else{
					// to check y axis configuration
					if (ydirection)
						for (int m =0;m<count;m++){
							coords[2*m]=tmp.getPosition(0).getX();
							coords[2*m+1]=tmp.getPosition(0).getY()-0.05*m;
						}
					else
						for (int m =0;m<count;m++){
							coords[2*m]=tmp.getPosition(0).getX();
							coords[2*m+1]=tmp.getPosition(0).getY()+0.05*m;
						}
					
					if (!tester.hasCollision(new ASVConfig(coords),obstacles)){
						System.out.println("y");
						solution.addAll(merge(new ASVConfig(coords),solution.get(solution.size()-1),obstacles));
					}
					else
						System.out.println("no passage");
				}
					
				
			
			}else
				solution.add(tmp);
			
		}

		
		return solution;
	}
	
	public static boolean isValid(ASVConfig conf,ASVConfig conf2){
		
		if (!tester.isValidStep(conf, conf2))
			return false;
		
		if (!tester.isConvex(conf))
			return false;
		
		if (!tester.hasEnoughArea(conf))
			return false;
		
		if (!tester.hasValidBoomLengths(conf))
			return false;
		
		
		return true;
	}
	
	public static void main(String args[]) throws Exception{
		
		ProblemSpec ps = new ProblemSpec();
		Node leaf = null;
		ArrayList<Node> list = new ArrayList<Node>();
		
		
		
		try{
			ps.loadProblem(args[0]);
		}catch(Exception e){
			e.printStackTrace();
			//System.out.println(e.printStackTrace());
		}
		
		ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
		
		obstacles = (ArrayList<Obstacle>) ps.getObstacles();
		ASVConfig source = ps.getInitialState();
		ASVConfig destination = ps.getGoalState();
		
		RapidTree rrt = new RapidTree(source.getPosition(0),destination.getPosition(0));// new RandomTree(source.getPosition(0),destination.getPosition(0),obstacles);
		System.out.println(destination.getPosition(0).toString());
		
		leaf = rrt.construct(obstacles);
		
		while(leaf != null){
			
			list.add(leaf);
			leaf = leaf.father;
		}
		
		Collections.reverse(list);
		
		ArrayList<ASVConfig> solution = makePath(list,  source,  destination, obstacles);
		System.out.println("x");
		ArrayList<ASVConfig> alist = merge(destination,solution.get(solution.size()-1),obstacles);
		solution.addAll(alist);
		
		double totalcost =0.0;
		
		for (int i = 1;i<solution.size();i++){
			
			for(int j=0;j<source.getASVCount();j++)
				totalcost = totalcost + solution.get(i).getPosition(j).distance(solution.get(i-1).getPosition(j));
		}
		
		System.out.println(solution.size());
		
		FileOutputStream resultOutput = new FileOutputStream(args[1]);
		
		resultOutput.write((Integer.toString(solution.size()-1)+" "+java.lang.Double.toString(totalcost)).getBytes());
		resultOutput.write("\r\n".getBytes());
		
		for(ASVConfig a : solution)
		{
			resultOutput.write(a.toString().getBytes());
			resultOutput.write("\r\n".getBytes());
		}
		
		resultOutput.close();
		
		//0.85, 0.225
		//	System.out.println(solution.size());
		
		
	}

}
