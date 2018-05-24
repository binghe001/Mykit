package io.mykit.filter.spring.controller;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 控制器代理
 * @author wdr
 *
 */
public class ControllerProxy implements MethodInterceptor {
    private BaseController controllerObj;
    private static final String PARAMETER = "parameter";

    private ControllerProxy(BaseController controllerObj) {
		super();
		this.controllerObj = controllerObj;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getProxy(BaseController controllerObj){
		ControllerProxy obj = new ControllerProxy(controllerObj);
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(controllerObj.getClass());
		enhancer.setCallback(obj);
        return (T)enhancer.create();
    }
    
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		//去除访问限制
        if ((!Modifier.isPublic(method.getModifiers()) || !Modifier.isPublic(method.getDeclaringClass().getModifiers()))
				&& !method.isAccessible()) {
			method.setAccessible(true);
		}

        //String methodName = method.getName();
        //判断调用的方法是否是RequestMapping方法，不是则不执行附加操作
        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
        if(requestMapping == null){
            return method.invoke(controllerObj,args);
        }

        //---------------------------执行到这里说明是调用的RequestMapping方法--------------------------------
        
        //获取参数方法名称
        String[] parameterNames = getMethodParamNames(controllerObj.getClass(),method);//getParamName(controllerObj.getClass(), methodName,method.getParameterTypes());
        if(parameterNames == null){
        	return method.invoke(controllerObj, args);
        }
        
        //获取parameter参数位置
        Integer pIndex = null;
        for(int i = 0; i < parameterNames.length; i++){
        	String parameterName = parameterNames[i];
        	if(PARAMETER.equals(parameterName) && !(args[i] instanceof String)){
        		pIndex = i;
        		break;
        	}
        }
        
         //TODO 如果存在parameter参数，则试图去找相应的请求参数，并将其转换为对象传给方法,具体待根据规则书写配置
        if(pIndex != null){
//        	String pStr = controllerObj.getRequest().getParameter(PARAMETER);
//        	if (StringUtils.isEmpty(pStr)) {
//    			ResponseHelper.responseMessage(null, false, true, MobileHttpCode.HTTP_PARAMETER_INVALID, controllerObj.getResponse());
//    			return null;
//    		}
//    		JSONObject jsonObject = JSONObject.fromObject(pStr);
//    		if (jsonObject == null) {
//    			ResponseHelper.responseMessage(null, false, true, MobileHttpCode.HTTP_PARAMETER_INVALID, controllerObj.getResponse());
//    			return null;
//    		}
//    		Object params = (Object) JSONObject.toBean(jsonObject, args[pIndex].getClass());
//    		args[pIndex] = params;
        }
        //调用入口方法
		return method.invoke(controllerObj, args);
	}

    /**
     *
     * <p>
     * 获取方法的参数名
     * </p>
     *
     * @param m
     * @return
     */
    public static String[] getMethodParamNames(Class<?> clazz,final Method m) {
        final String[] paramNames = new String[m.getParameterTypes().length];
        ClassReader cr = null;
        try {
            String className = clazz.getName();
            int lastDotIndex = className.lastIndexOf(".");
            String classFileName = className.substring(lastDotIndex + 1) + ".class";
            InputStream is = clazz.getResourceAsStream(classFileName);
            cr = new ClassReader(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        cr.accept(new ClassVisitor(Opcodes.ASM4) {
            @Override
            public MethodVisitor visitMethod(final int access,
                                             final String name, final String desc,
                                             final String signature, final String[] exceptions) {
                final Type[] args = Type.getArgumentTypes(desc);
                // 方法名相同并且参数个数相同
                if (!name.equals(m.getName())
                        || !sameType(args, m.getParameterTypes())) {
                    return super.visitMethod(access, name, desc, signature,
                            exceptions);
                }
                MethodVisitor v = super.visitMethod(access, name, desc,
                        signature, exceptions);
                return new MethodVisitor(Opcodes.ASM4, v) {
                    @Override
                    public void visitLocalVariable(String name, String desc,
                                                   String signature, Label start, Label end, int index) {
                        int i = index - 1;
                        // 如果是静态方法，则第一就是参数
                        // 如果不是静态方法，则第一个是"this"，然后才是方法的参数
                        if (Modifier.isStatic(m.getModifiers())) {
                            i = index;
                        }
                        if (i >= 0 && i < paramNames.length) {
                            paramNames[i] = name;
                        }
                        super.visitLocalVariable(name, desc, signature, start,
                                end, index);
                    }

                };
            }
        }, 0);
        return paramNames;
    }

    /**
     *
     * <p>
     * 比较参数类型是否一致
     * </p>
     *
     * @param types
     *            asm的类型({@link Type})
     * @param clazzes
     *            java 类型({@link Class})
     * @return
     */
    private static boolean sameType(Type[] types, Class<?>[] clazzes) {
        // 个数不同
        if (types.length != clazzes.length) {
            return false;
        }

        for (int i = 0; i < types.length; i++) {
            if (!Type.getType(clazzes[i]).equals(types[i])) {
                return false;
            }
        }
        return true;
    }
}
