package com.el.returnmsg.dao;

import com.el.returnmsg.mapper.F551513Z;
import com.el.returnmsg.model.F551513ZJson;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.List;

/**
 * Created by Alan on 2020/10/10 16:53
 */
@Repository
@Mapper
public interface F551513ZDao {
    @Select("select * from f551513z where axedsp = #{edsp} and axactn=#{actn}")
    List<F551513Z>  queryF551513ZList(@Param("edsp")String edsp,@Param("actn")String actn);

    @Update("update f551513z set axedsp='Y' where axpid =#{pid}")
    int updateF551513Z(@Param("pid")String pid);

    @DeleteProvider(type = Provider.class, method = "batchDelete")
    int deleteF551513ZList(F551513ZJson inputJson);
    @DeleteProvider(type = Provider.class, method = "batchDelete2")
    int deleteF551511ZList(F551513ZJson inputJson);
    class Provider {
//        /* 批量插入 */
//        public String batchInsert(Map map) {
//            List<Student> students = (List<Student>) map.get("list");
//            StringBuilder sb = new StringBuilder();
//            sb.append("INSERT INTO student (name,sex,addr) VALUES ");
//            MessageFormat mf = new MessageFormat(
//                    "(#'{'list[{0}].name}, #'{'list[{0}].sex}, #'{'list[{0}].addr})"
//            );

//            for (int i = 0; i < students.size(); i++) {
//                sb.append(mf.format(new Object[] {i}));
//                if (i < students.size() - 1)
//                    sb.append(",");
//            }
//            return sb.toString();
//        }

        /* 批量删除 */
        public String batchDelete(F551513ZJson inputJson) {
            List<Integer> students = inputJson.getPids();
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM f1511b WHERE njpost!='D' AND trim(njpid) IN ('");
//            sb.append("DELETE FROM F551511Z WHERE   trim(AXpid) IN (");
            for (int i = 0; i < students.size(); i++) {
                sb.append(students.get(i).toString());

                if (i < students.size() - 1)
                    sb.append("','");
            }
            sb.append("')");
            return sb.toString();
        }

        /* 批量删除 */
        public String batchDelete2(F551513ZJson inputJson) {
            List<Integer> students = inputJson.getPids();
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM F551511Z WHERE  trim(axpid) IN ('");
//            sb.append("DELETE FROM F551511Z WHERE   trim(AXpid) IN (");
            for (int i = 0; i < students.size(); i++) {
                sb.append(students.get(i).toString());

                if (i < students.size() - 1)
                    sb.append("','");
            }
            sb.append("')");
            return sb.toString();
        }
    }
}
