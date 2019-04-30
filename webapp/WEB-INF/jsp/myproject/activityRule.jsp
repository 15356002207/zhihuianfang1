<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="活动规则表" base="http" lib="ext">
<aos:body>
</aos:body>
</aos:html>
<aos:onready>
	<aos:viewport layout="border">
		<aos:formpanel id="_f_query" layout="column" labelWidth="70" header="false" region="north" border="false">
			<aos:docked forceBoder="0 0 1 0">
				<aos:dockeditem xtype="tbtext" text="查询条件" />
			</aos:docked>
			<%--
			<aos:textfield name="name_" fieldLabel="持卡人姓名" columnWidth="0.25" />
			<aos:combobox name="card_type_" fieldLabel="卡类型" dicField="card_type_" columnWidth="0.25" />
			--%>
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="查询" onclick="_g_activityRule_query" icon="query.png" />
				<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(_f_query);" icon="refresh.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>

		<aos:gridpanel id="_g_activityRule" url="activityRuleController.listActivityRule" onrender="_g_activityRule_query"
			onitemdblclick="#_w_activityRule2.show();" region="center">
			<aos:menu>
				<aos:menuitem text="新增" onclick="#_w_activityRule.show();AOS.reset(_f_activityRule);" icon="add.png" />
				<aos:menuitem text="修改" onclick="_g_activityRule_update" icon="edit.png" />
				<aos:menuitem text="删除" onclick="fn_del()" icon="del.png" />
				<aos:menuitem xtype="menuseparator" />
				<aos:menuitem text="刷新" onclick="#_g_activityRule_store.reload();" icon="refresh.png" />
			</aos:menu>
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="活动规则表信息" />
				<aos:dockeditem xtype="tbseparator" />
				<aos:dockeditem text="新增" icon="add.png" onclick="#_w_activityRule.show();AOS.reset(_f_activityRule);" />
				<aos:dockeditem text="修改" icon="edit.png" onclick="_g_activityRule_update" />
				<aos:dockeditem text="批量删除" icon="del.png" onclick="_g_activityRule_delAll" />
			</aos:docked>
			<aos:selmodel type="checkbox" mode="multi" />
			<aos:column type="rowno" />
			<aos:column header="主键" dataIndex="ar_id" hidden="true" />	
			<aos:column header="活动ID" dataIndex="activity_id" width="100" />	
			<aos:column header="规则名称" dataIndex="name_" width="100" />	
			<aos:column header="符号" dataIndex="symbol" width="100" />	
			<aos:column header="参数" dataIndex="param" width="100" />	
			<aos:column header="表达式" dataIndex="expression" width="100" />	
			<aos:column header="描述" dataIndex="desc_" width="100" />	
	
			<%-- 数据字典、时间例子
			<aos:column header="卡类型" dataIndex="card_type_" rendererField="card_type_" width="60" />
			<aos:column header="出生日期" dataIndex="birthday_" type="date" format="Y-m-d H:i:s" width="100" />
			--%>
			<aos:column header="修改" rendererFn="fn_button_render" align="center" fixedWidth="50" />
			<aos:column header="删除" rendererFn="fn_button_del" align="center" fixedWidth="50" />
		</aos:gridpanel>

		<%-- 新增窗口 --%>
		<aos:window id="_w_activityRule" title="新增账户">
			<aos:formpanel id="_f_activityRule" width="450" layout="anchor" labelWidth="70">
			<aos:textfield name="activity_id" fieldLabel="活动ID" allowBlank="false" />
			<aos:textfield name="name_" fieldLabel="规则名称" allowBlank="false" />
			<aos:textfield name="symbol" fieldLabel="符号" allowBlank="false" />
			<aos:textfield name="param" fieldLabel="参数" allowBlank="false" />
			<aos:textfield name="expression" fieldLabel="表达式" allowBlank="false" />
			<aos:textfield name="desc_" fieldLabel="描述" allowBlank="false" />
				<%-- 数据字典、时间例子
				<aos:datefield name="birthday_" fieldLabel="出生日期" editable="false" allowBlank="false" />
				<aos:textfield name="card_id_" fieldLabel="信用卡号" allowBlank="false" />
				<aos:combobox name="card_type_" fieldLabel="卡类型" dicField="card_type_" value="1" allowBlank="false" />
				<aos:numberfield name="credit_line_" minValue="500" maxValue="999999" fieldLabel="信用额度" allowBlank="false" />
				<aos:textareafield name="address_" fieldLabel="持卡人住址" />
				--%>
			</aos:formpanel>
			<aos:docked dock="bottom" ui="footer">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem onclick="_f_activityRule_save" text="保存" icon="ok.png" />
				<aos:dockeditem onclick="#_w_activityRule.hide();" text="关闭" icon="close.png" />
			</aos:docked>
		</aos:window>

		<%-- 修改窗口 --%>
		<aos:window id="_w_activityRule2" title="修改账户" onshow="_w_activityRule2_onshow">
			<aos:formpanel id="_f_activityRule2" width="450" layout="anchor" labelWidth="70">
				<%-- 更新时的隐藏变量(主键) --%>
				
			<aos:hiddenfield name="ar_id" fieldLabel="主键" />
			<aos:textfield name="activity_id" fieldLabel="活动ID" allowBlank="false" />
			<aos:textfield name="name_" fieldLabel="规则名称" allowBlank="false" />
			<aos:textfield name="symbol" fieldLabel="符号" allowBlank="false" />
			<aos:textfield name="param" fieldLabel="参数" allowBlank="false" />
			<aos:textfield name="expression" fieldLabel="表达式" allowBlank="false" />
			<aos:textfield name="desc_" fieldLabel="描述" allowBlank="false" />
				<%-- 数据字典、时间例子
				<aos:datefield name="birthday_" fieldLabel="出生日期" editable="false" allowBlank="false" />
				<aos:textfield name="card_id_" fieldLabel="信用卡号" allowBlank="false" />
				<aos:combobox name="card_type_" fieldLabel="卡类型" dicField="card_type_" value="1" allowBlank="false" />
				<aos:numberfield name="credit_line_" minValue="500" maxValue="999999" fieldLabel="信用额度" allowBlank="false" />
				<aos:textareafield name="address_" fieldLabel="持卡人住址" />
				--%>
			</aos:formpanel>
			<aos:docked dock="bottom" ui="footer">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem onclick="_f_activityRule2_update" text="保存" icon="ok.png" />
				<aos:dockeditem onclick="#_w_activityRule2.hide();" text="关闭" icon="close.png" />
			</aos:docked>
		</aos:window>

	</aos:viewport>

	<script type="text/javascript">
	
		//加载表格数据
		function _g_activityRule_query() {
			var params = AOS.getValue('_f_query');
			_g_activityRule_store.getProxy().extraParams = params;
			_g_activityRule_store.loadPage(1);
		}
		
		//新增账户保存
		function _f_activityRule_save(){
				AOS.ajax({
					forms : _f_activityRule,
					url : 'activityRuleController.saveActivityRule',
					ok : function(data) {
						 AOS.tip(data.appmsg);
						_g_activityRule_store.reload();
						_w_activityRule.hide();
					}
			});
		}
		//窗口弹出事件监听
		function _w_activityRule_onshow() {
			var record = AOS.selectone(_g_activityRule);
            AOS.ajax({
            	params : {
            			        ar_id: record.data.ar_id
            	},
                url: 'activityRuleController.getActivityRule',
                ok: function (data) {
                	_f_activityRule2.form.setValues(data);
                }
            });
		}
		//监听修改窗口弹出
		function _w_activityRule2_onshow(){
			//这里演示的是直接从表格行中加载数据，也可以发一个ajax请求查询数据(见misc1.jsp有相关用法)
			var record = AOS.selectone(_g_activityRule, true);
			_f_activityRule2.loadRecord(record);
		}
		
		//修改账户保存
		function _f_activityRule2_update(){
				AOS.ajax({
					forms : _f_activityRule2,
					url : 'activityRuleController.updateActivityRule',
					ok : function(data) {
						AOS.tip(data.appmsg);
						_g_activityRule_store.reload();
						_w_activityRule2.hide();
					}
			});
		}
		
		 //更新
	 	function _g_activityRule_update(){
	 		
				if(AOS.selectone(_g_activityRule)){
					//_w_activityRule2_onshow();
					AOS.get('_w_activityRule2').show();
				}
				
				
		}
		
        //删除账户信息
	 	function _g_activityRule_delAll(){
	        	var selection = AOS.selection(_g_activityRule, 'ar_id');
				
				if(AOS.empty(selection)){
					AOS.tip('删除前请先选中数据。');
					return;
				}
				var rows = AOS.rows(_g_activityRule);
				var msg =  AOS.merge('确认要删除选中的{0}条记录吗？', rows);
				AOS.confirm(msg, function(btn){
					if(btn === 'cancel'){
						AOS.tip('删除操作被取消。');
						return;
					}
					AOS.ajax({
						url : 'activityRuleController.deleteActivityRule',
						params:{
							aos_rows_: selection
						},
						ok : function(data) {
							AOS.tip(data.appmsg);
							_g_activityRule_store.reload();
						}
					});
				});
			}
		
		//按钮列转换
		function fn_button_render(value, metaData, record) {
			return '<input type="button" value="修改" class="cbtn_danger" onclick="_w_activityRule2_show();" />';
		}
		
		//按钮列转换
		function fn_button_del(value, metaData, record) {
			return '<input type="button" value="删除" class="cbtn_danger" onclick="fn_del();" />';
		}
		
	</script>
</aos:onready>

<script type="text/javascript">

function _w_activityRule2_show(){
	AOS.get('_w_activityRule2').show();
}




function fn_del(){
	//由于是列按钮对应的函数，下面获取对象的的写法和onready代码段里的js有些不同
	var record = AOS.selectone(AOS.get('_g_activityRule'));
	var msg =  AOS.merge('确认要删除当前记录吗？', "");
	AOS.confirm(msg, function(btn){
		if(btn === 'cancel'){
			AOS.tip('删除操作被取消。');
			return;
		}
		AOS.ajax({
			url : 'activityRuleController.deleteActivityRule',
			params:{
	        ar_id: record.data.ar_id
			},
			ok : function(data) {
				AOS.tip(data.appmsg);
				AOS.get('_g_activityRule').getStore().reload();
			}
		});
	});
}

</script>