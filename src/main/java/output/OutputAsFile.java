package output;

import task.TaskManager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OutputAsFile {
	public void output( String dirName, String fileName, TaskManager tm, String extension ) {
		String path = setPath( dirName, fileName, tm, extension );
		PrintWriter pw = makeNewPrintWriter( path );
		// 書き込み動作
		pw.close();
	}

	private String setPath( String dir_name, String file_name, TaskManager tm, String extension ) {
		String currentPath = System.getProperty( "user.dir" );
		Date date = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat( ",yyyy_MM_dd,HH_mm_ss" );
		File dir = new File( currentPath + "/out/" + dir_name );

		if( ! Files.exists( Paths.get( currentPath + "/out/" + dir_name ) ) ) {
			dir.mkdir();
		}
		// todo: task追加数を入れる
		double x = 0;
		return currentPath + "/out/" + dir_name + "/" + file_name + ",λ=" + String.format( "%.2f", x ) + sdf1.format( date ) + "." + extension;
	}

	private PrintWriter makeNewPrintWriter( String path ) {
		try {
			FileWriter fw = new FileWriter( path, false );
			BufferedWriter bw = new BufferedWriter( fw );
			return new PrintWriter( bw );
		} catch ( IOException e ) {
			e.printStackTrace();
		}
		return null;
	}


}
