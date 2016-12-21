<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="bjui-pageContent">
    <form action="<%=request.getContextPath()%>/cp/add.do" data-toggle="validate" data-reload-navtab="true">
       <%--  <input type="hidden" name="id" value="${info.id}">
        <input type="hidden" name="cplxid" value="${info.cplxid }"> --%>
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover" width="100%">
                <tbody>
                    <tr>
                        <td colspan="2">
                            <label class="control-label x100">菜谱名：</label>
                            <input type="text" name="cpname"  size="60">
                        </td>
                    </tr>
                      <tr>
                        <td colspan="2">
                            <label class="control-label x100">菜谱类型：</label>
                            <input type="text" name="cplx"  size="60">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <label class="control-label x100">备注：</label>
                            <div style="display: inline-block; vertical-align: middle;">
                                <textarea name="bz" style="width:784px;height:50px" data-toggle="kindeditor"></textarea>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </form>
</div>

<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
        <li><button type="submit" class="btn-default" data-icon="save">保存</button></li>
    </ul>
</div>