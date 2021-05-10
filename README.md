# dcm-poi
## --------------
#### 1、介绍
 文件操作系统，根据POI改装操作源码。类似EasyPOI的操作工具，优化了一下部分EasyPOI的缺陷。
 主打的功能就是让一个没见接触过poi的人员可以方便的写出Excel导出,Excel模板导出,Excel导入,Word模板导出,通过简单的注解和模板语言(熟悉的表达式语法),完成以前复杂的写法
## --------------
#### 2、软件架构
##### 2.1、工程说明
    1. dcm-poi 父包--作用大家都懂得
    2. dcm-poi-annotation 基础注解包,作用与实体对象上,拆分后方便maven多工程的依赖管理
    3. dcm-poi-base 导入导出的工具包,可以完成Excel导出,导入,Word的导出,Excel的导出功能
    4. dcm-poi-web  耦合了spring-mvc 基于AbstractView,极大的简化spring-mvc下的导出功能

## --------------
#### 3、安装教程
###### 3.1、Maven
		 <dependency>
			<groupId>com.dcm</groupId>
			<artifactId>dcm-poi-base</artifactId>
			<version>4.3.0</version>
		</dependency>
		<dependency>
			<groupId>com.dcm</groupId>
			<artifactId>dcm-poi-web</artifactId>
			<version>4.3.0</version>
		</dependency>
		<dependency>
			<groupId>com.dcm</groupId>
			<artifactId>dcm-poi-annotation</artifactId>
			<version>4.3.0</version>
		</dependency>
###### 3.2、pom说明
        <!-- sax 读取时候用到的 -->
			<dependency>
				<groupId>xerces</groupId>
				<artifactId>xercesImpl</artifactId>
				<version>${xerces.version}</version>
				<optional>true</optional>
			</dependency>
			<dependency>
				<groupId>org.apache.poi</groupId>
				<artifactId>poi-scratchpad</artifactId>
				<version>${poi.version}</version>
				<optional>true</optional>
			</dependency>
			
			<!-- Word 需要使用 -->
            <dependency>
                <groupId>org.apache.poi</groupId>
                <artifactId>ooxml-schemas</artifactId>
                <version>1.3</version>
                <optional>true</optional>
            </dependency>
			
			<!-- 校验,下面两个实现 -->
			<dependency>
				<groupId>org.hibernate</groupId>
				<artifactId>hibernate-validator</artifactId>
				<version>5.1.3.Final</version>
				<optional>true</optional>
			</dependency>
			
			<dependency>
				<groupId>org.apache.bval</groupId>
				<artifactId>org.apache.bval.bundle</artifactId>
				<version>1.1.0</version>
			</dependency>
			
			<!-- PDF -->
			<dependency>
				<groupId>com.itextpdf</groupId>
				<artifactId>itextpdf</artifactId>
				<version>5.5.6</version>
				<optional>true</optional>
			</dependency>

			<dependency>
				<groupId>com.itextpdf</groupId>
				<artifactId>itext-asian</artifactId>
				<version>5.2.0</version>
				<optional>true</optional>
			</dependency>
## --------------
#### 4、使用说明
##### 4.1、导出
  ###### 1.正规excel导出 (格式简单,数据量可以,5W以内吧)
       注解方式:  ExcelExportUtil.exportExcel(ExportParams entity, Class<?> pojoClass,Collection<?> dataSet)
  ###### 2.不定多少列,但是格式依然简单数据库不大
       自定义方式: ExcelExportUtil.exportExcel(ExportParams entity, List<ExcelExportEntity> entityList,Collection<?> dataSet)
  ###### 3.数据量大超过5W,还在100W以内
       注解方式 ExcelExportUtil.exportBigExcel(ExportParams entity, Class<?> pojoClass,IExcelExportServer server, Object queryParams)
       自定义方式: ExcelExportUtil.exportBigExcel(ExportParams entity, List<ExcelExportEntity> excelParams,IExcelExportServer server, Object queryParams)
  ###### 4.样式复杂,数据量尽量别大
       模板导出 ExcelExportUtil.exportExcel(TemplateExportParams params, Map<String, Object> map)
  ###### 5.一次导出多个风格不一致的sheet
       模板导出：ExcelExportUtil.exportExcel(Map<Integer, Map<String, Object>> map,TemplateExportParams params)
  ###### 6.一个模板但是要导出非常多份
       模板导出 ExcelExportUtil.exportExcelClone(Map<Integer, List<Map<String, Object>>> map,TemplateExportParams params)
  ###### 7.模板无法满足你的自定义,试试html
       自己构造html,然后我给你转成excel  ExcelXorHtmlUtil.htmlToExcel(String html, ExcelType type)
  ###### 8.数据量过百万级了.放弃excel吧,csv导出
       注解方式: CsvExportUtil.exportCsv(CsvExportParams params, Class<?> pojoClass, OutputStream outputStream)
       自定义方式: CsvExportUtil.exportCsv(CsvExportParams params, List<ExcelExportEntity> entityList, OutputStream outputStream)
  ###### 9.word导出
       模板导出: WordExportUtil.exportWord07(String url, Map<String, Object> map)
  ###### 10.PDF导出
       模板导出: TODO
##### 4.2、导入
       如果想提高性能 ImportParams 的concurrentTask 可以帮助并发导入,仅单行,最小1000。excel有单个的那种特殊读取,readSingleCell 参数可以支持
  ###### 1. 不需要检验,数据量不大(5W以内)
       注解或者MAP: ExcelImportUtil.importExcel(File file, Class<?> pojoClass, ImportParams params)
  ###### 2. 需要导入,数据量不大
       注解或者MAP: ExcelImportUtil.importExcelMore(InputStream inputstream, Class<?> pojoClass, ImportParams params)
  ###### 3. 数据量大了,或者你有特别多的导入操作,内存比较少,仅支持单行
       SAX方式  ExcelImportUtil.importExcelBySax(InputStream inputstream, Class<?> pojoClass, ImportParams params, IReadHandler handler)
  ###### 4. 数据量超过EXCEL限制,CSV读取
       小数据量: CsvImportUtil.importCsv(InputStream inputstream, Class<?> pojoClass,CsvImportParams params)
       大数据量: CsvImportUtil.importCsv(InputStream inputstream, Class<?> pojoClass,CsvImportParams params, IReadHandler readHandler)
## --------------
#### 5、参与贡献
###### EasyPOI
###### POI
