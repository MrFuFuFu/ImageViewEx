/*
	Android Asynchronous HttpURLConnection
	Copyright 2011 Chris Roemmich <chris@cr-wd.com>
	https://cr-wd.com

	Licensed under the Apache License, Version 2.0 (the "License");
 	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

		http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
 */

/*
 Adapted from https://github.com/loopj/android-async-http/blob/master/src/com/loopj/android/http/RequestParams.java
 */

package mrfu.imageviewex.lib.http;

import android.text.TextUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class RequestParams implements Serializable {

	private static final long serialVersionUID = -6376078803512393464L;

	protected String encoding = "UTF-8";

	protected ConcurrentMap<String, Object> urlParams = new ConcurrentHashMap<String, Object>();
	protected ConcurrentMap<String, FileWrapper> fileParams = new ConcurrentHashMap<String, FileWrapper>();

	/**
	 * Creates a new HttpParams object
	 */
	public RequestParams() {}

	
	/**
	 * Constructs a new RequestParams instance containing the key/value string
	 * params from the specified map.
	 * 
	 * @param source
	 *            the source key/value string map to add.
	 */
	public RequestParams(final Map<String, String> source) {
		if(null ==source)
			return;
		for (final Map.Entry<String, String> entry : source.entrySet()) {
			put(entry.getKey(), entry.getValue());
		}
	}
	
	public Object get(String key){
		return urlParams.get(key);
	}

	/**
	 * Constructs a new RequestParams instance and populate it with a single
	 * initial key/value string param.
	 * 
	 * @param key
	 *            the key name for the intial param.
	 * @param value
	 *            the value string for the initial param.
	 */
	public RequestParams(final String key, final String value) {
		put(key, value);
	}

	public Map<String,Object> getUrlParams(){
		return urlParams;
	}
	public Map<String,FileWrapper> getFileParams(){
		return this.fileParams;
	}
	/**
	 * Adds a key/value string pair to the request.
	 * 
	 * @param key
	 *            the key name for the new param.
	 * @param value
	 *            the value string for the new param.
	 */
	public void put(final String key, final String value) {
		if (key != null && value != null) {
			urlParams.put(key, value);
		}
	}
	
	public void put(final String key, final Object value) {
		if (key != null && value != null) {
			urlParams.put(key, value);
		}
	}
	
	/**
	 * �?�????�?
	 * @param key
	 * @param fileWrapper
	 */
	public void put(final String key,FileWrapper fileWrapper){
		fileParams.put(key,fileWrapper);
	}

	/**
	 * Adds a key/value pair to the request. Calls Object.toString() to
	 * determine value.
	 * 
	 * @param key
	 * @param value
	 */
//	public void put(final String key, final Object value) {
//		put(key, value.toString());
//	}

	/**
	 * Adds a file to the request.
	 * 
	 * @param key
	 *            the key name for the new param.
	 * @param file
	 *            the file to add.
	 */
//	public void put(final String key, final File file) throws FileNotFoundException {
//		put(key, file.getAbsolutePath(), file.getName());
//	}

//	public void put(final String key,final String filePath, final String fileName){
//		fileParams.put(key, new FileWrapper(filePath, fileName, null));
//	}
	/**
	 * Adds an input stream to the request.
	 * 
	 * @param key
	 *            the key name for the new param.
	 * @param stream
	 *            the input stream to add.
	 */
//	public void put(final String key, final InputStream stream) {
//		put(key, stream, null);
//	}

	/**
	 * Adds an input stream to the request.
	 * 
	 * @param key
	 *            the key name for the new param.
	 * @param stream
	 *            the input stream to add.
	 * @param fileName
	 *            the name of the file.
	 */
//	public void put(final String key, final InputStream stream, final String fileName) {
//		put(key, stream, fileName, null);
//	}

	/**
	 * Adds an input stream to the request.
	 * 
	 * @param key
	 *            the key name for the new param.
	 * @param stream
	 *            the input stream to add.
	 * @param fileName
	 *            the name of the file.
	 * @param contentType
	 *            the content type of the file, eg. application/json
	 */
//	public void put(final String key, final InputStream stream, final String fileName, final String contentType) {
//		if (key != null && stream != null) {
//			fileParams.put(key, new FileWrapper(stream, fileName, contentType));
//		}
//	}

	/**
	 * Removes a parameter from the request.
	 * 
	 * @param key
	 *            the key name for the parameter to remove.
	 */
	public void remove(final String key) {
		urlParams.remove(key);
		fileParams.remove(key);
	}

//	@Override public String toString() {
//		final StringBuilder result = new StringBuilder();
//		for (final ConcurrentHashMap.Entry<String, String> entry : urlParams.entrySet()) {
//			if (result.length() > 0) {
//				result.append("&");
//			}
//
//			result.append(URLEncoder.encode(entry.getKey()));
//			result.append("=");
//			result.append(URLEncoder.encode(entry.getValue()));
//		}
//
//		for (final ConcurrentHashMap.Entry<String, FileWrapper> entry : fileParams.entrySet()) {
//			if (result.length() > 0) {
//				result.append("&");
//			}
//
//			result.append(URLEncoder.encode(entry.getKey()));
//			result.append("=");
//			result.append("FILE");
//		}
//
//		return result.toString();
//	}

//	protected List<BasicNameValuePair> getParamsList() {
//		final List<BasicNameValuePair> lparams = new LinkedList<BasicNameValuePair>();
//
//		for (final ConcurrentHashMap.Entry<String, String> entry : urlParams.entrySet()) {
//			lparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//		}
//
//		return lparams;
//	}

//	public String getParamString() {
//		return URLEncodedUtils.format(getParamsList(), encoding);
//	}

	protected boolean hasMultipartParams() {
		return !fileParams.isEmpty();
	}

	public static class FileWrapper {
		public String filePath;
		public String fileType;

		public String getFilePath() {
			return filePath;
		}

		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}
		
		public String  getFileName(){
			return this.fileType;
		}
		
		public FileWrapper(String filePath,String fileType){
			this.filePath = filePath;
			this.fileType = fileType;
		}

		public String getFileType() {
			return fileType;
		}

		public void setFileType(String fileType) {
			this.fileType = fileType;
		}
		
		public int getLength(){
			if(!TextUtils.isEmpty(filePath)){
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(filePath);
					return fis.available();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}finally
				{
					if(fis != null){
						try {
							fis.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
			return 0;
		}
		
	}
}