package com.mrpan.wechat.bean.results.utils;

import java.io.Writer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * 是否使用	<![CDATA[]}>标签
 */
public class XStreamFactory {
	private static final String START_CADA = "<![CDATA[";
	private static final String END_CADA="]]>";

	/**
	 * 是否启用<![CDATA[]]>
	 * @param isdata	true 表示启用 	false表示不启用
	 * @return 
	 */
	public static XStream init(boolean isdata){
		XStream xstream = null;
		if(isdata){
			xstream = new XStream(new XppDriver(){
				public HierarchicalStreamWriter createWriter(Writer out){
					return new PrettyPrintWriter(out){
						@Override
						protected void writeText(QuickWriter writer, String text) {
							if(!text.startsWith(START_CADA)){
								text = START_CADA+text+END_CADA;
							}
							writer.write(text);
						}			
					};
				}
			});
		}else{
			xstream = new XStream();
		}
		return xstream;
	}
}