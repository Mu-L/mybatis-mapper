package ${package};

import io.mybatis.provider.Entity;
import io.mybatis.provider.extend.Extend;
import org.apache.ibatis.type.JdbcType;

<#list it.importJavaTypes as javaType>
import ${javaType};
</#list>

/**
 * ${it.name} - ${it.comment}
 *
 * @author ${SYS['user.name']}
 */
@Extend.Table("${it.name}" remark = "${it.comment}", autoResultMap = true)
public class ${it.name.className} {
  <#list it.columns as column>
  <#if column.pk>
  @Extend.Column(value = "${column.name}", id = true, remark = "${column.comment}", updatable = false, insertable = false)
  <#else>
  @Extend.Column(value = "${column.name}", remark = "${column.comment}"<#if column.tags.jdbcType>, jdbcType = JdbcType.${column.jdbcType}</#if>)
  </#if>
  private ${column.javaType} ${column.name.fieldName};

  </#list>

  <#list it.columns as column>
  /**
   * 获取 ${column.comment}
   *
   * @return ${column.name.fieldName} - ${column.comment}
   */
  public ${column.javaType} get${column.name.className}() {
    return ${column.name.fieldName};
  }

  /**
   * 设置${column.comment}
   *
   * @param ${column.name.fieldName} ${column.comment}
   */
  public void set${column.name.className}(${column.javaType} ${column.name.fieldName}) {
    this.${column.name.fieldName} = ${column.name.fieldName};
  }

  </#list>
}
