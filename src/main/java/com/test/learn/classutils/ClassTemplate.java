/*
 * @Project Name: springmvcdemo
 * @File Name: ClassTemplate.java
 * @Package Name: com.test.learn.classutils
 * @Date: 2017年11月24日上午11:45:36
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.classutils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.storm.command.list;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.springmvc.utils.StringUtils;

/**
 * 
 * 
 * @author zengzw-1220
 * @date 2017年11月24日上午11:45:36
 * @see
 */
public abstract class ClassTemplate {

	protected final String packageName;

	public ClassTemplate(String packageName) {
		this.packageName = packageName;
	}


	public final List<Class<?>> getListClass() throws IOException{
		List<Class<?>> listClass = new ArrayList<>();

		//获取包相关信息
		Enumeration<URL> enumeration = ClassUtils.getClassloader().getResources(packageName.replaceAll(".","/"));
		while(enumeration.hasMoreElements()){
			URL url = enumeration.nextElement();
			//获取协议名（jar、file)
			String protocal = url.getProtocol();

			//如果是File
			if(protocal.equals("file")){
				String packagePath = url.getPath().replaceAll("%20", " ");

				addClass(listClass, packagePath, packageName);

			}else{
				//如果是Jar包
				JarURLConnection jarURLConnection = (JarURLConnection)url.openConnection();
				JarFile jarFile = jarURLConnection.getJarFile();
				Enumeration<JarEntry> jarEnumeration = jarFile.entries();
				
				while(jarEnumeration.hasMoreElements()){
					JarEntry jarEntry = jarEnumeration.nextElement();
					String jarName = jarEntry.getName();
					if(jarName.endsWith(".class")){
						String className = jarName.substring(0, jarName.lastIndexOf(".")).replaceAll("/"
								,".");
						
						doAddClass(listClass, className);
					}
				}
			}
		}
		
		return listClass;

	}


	/*
	 * 添加Class
	 */
	private void addClass(List<Class<?>> classList, String packagePath, String packageName) {
		//得到包路径下所有的文件 和  文件夹
		File[] files = new File(packagePath).listFiles(new FileFilter() {

			@Override
			public boolean accept(File file) {

				return (file.isFile() && file.getName().endsWith(".class")|| file.isDirectory());
			}
		});

		//循环遍历，添加类
		for(File file : files){
			String fileName = file.getName();
			if(file.isFile()){
				//处理文件
				String className = fileName.substring(0,fileName.lastIndexOf("."));
				if(StringUtils.isNotEmpty(className)){
					className = packageName + "." + className;
				}

				doAddClass(classList, className);

			}else{
				//递归处理包下面的文件

				//获取子包
				String subPackagePatch = fileName;
				if(StringUtils.isNotEmpty(packagePath)){
					subPackagePatch = packagePath + "/" + subPackagePatch;
				}

				//获取子包名称
				String subPackageName = fileName;
				if(StringUtils.isNotEmpty(packageName)){
					subPackageName = packageName + "." + subPackageName;
				}

				//递归调用
				addClass(classList, subPackagePatch, subPackageName);
			}
		}


	}


	/*
	 * 添加类
	 */
	private void doAddClass(List<Class<?>> listClass,String className){
		Class<?> clz = ClassUtils.loadClass(className,false);

		if(checkAddClass(clz)){
			listClass.add(clz);
		}
	}

	public abstract boolean checkAddClass(Class<?> clz);

	public static void main(String[] args) {

		System.out.println(File.separator);
		System.out.println(File.pathSeparator);
	}
}
