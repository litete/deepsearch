package app.home.function;

import java.util.ArrayList;
import java.util.List;

public class CosineDistance {
	public class Vector{
		public int dem = 300;//400,200,100向量的维数
		public double[] value = new double[dem];

	}
	protected Vector userVector = new Vector();
	
	/**
	 * 默认构造函数，初始化一个全为0的向量作为用户向量的初始值
	 */
	public CosineDistance(){
		Vector v = new Vector();//new a default vector
		for(int i=0;i<v.dem;i++){
			v.value[i] = 0.0;//initialize
		}
		userVector = v;
	}
	
	/**
	 * 根据传入的文章向量，更新用户向量
	 * @param article
	 * @return
	 */
	public Vector updateUserVector(Vector article){
		Vector res = new Vector();
		if(article == null || article.value.length == 0)return null;
		for(int i=0;i<res.dem;i++){//
			res.value[i] = (article.value[i] + this.userVector.value[i])/2.0;
		}
		return res;
	}
	
	/**
	 * 计算两个向量的余弦距离
	 * 返回数值为-1~1之间的double数值，1表示最接近
	 * @param A
	 * @param B
	 * @return
	 */
	public double calculateConsineDistance(Vector A,Vector B){
		double Ai = 0;
		double Bi = 0;
		double AiBi = 0;
		//ai的平方的和的平方根
		for(int i=0;i<A.value.length;i++){
			double ai = A.value[i];
			double aiSquare = ai*ai;
			Ai += aiSquare;
		}
		//bi的平方的和的平方根
		for(int i=0;i<B.value.length;i++){
			double bi = B.value[i];
			double biSquare = bi*bi;
			Bi += biSquare;
		}
		//ai*bi的和
		for(int i=0;i<A.value.length;i++){
			double ai = A.value[i];
			double bi = B.value[i];
			AiBi += ai*bi;
		}
		double button = (Math.sqrt(Ai))*(Math.sqrt(Bi));
		double res = AiBi/button;
		return res;
		
	}
}
