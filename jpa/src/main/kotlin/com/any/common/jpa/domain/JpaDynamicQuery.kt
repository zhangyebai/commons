package com.any.common.jpa.domain

/**
 * javadoc JpaDynamicQuery
 * <p>
 *     jpa 扩展动态查询标记
 * <p>
 * @author zhang yebai
 * @date 2021/7/22 3:18 PM
 * @version 1.0.0
 **/
interface JpaDynamicQuery

// JpaDynamicQuery 使用举例

/*
    {code}
    public class UserEntity{
        private int id;

        private String userId;

        private String userName;
    }
    {code}

        {code}
        public class UserEntityQuery implements JpaDynamicQuery{
            // for id equals condition
            private JpaEq<Integer> idEq;

            // for id in [xx, xx]
            private JpaIn<Integer> idIn;

            // for left like, right like, or full like
            private JpaLike<String> userNameLike;

            // for user id eq
            private JpaEq<String> userIdEq;
        }
    {code}
 */