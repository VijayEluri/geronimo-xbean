/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.xbean.finder;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @version $Rev$ $Date$
 */
public class FilteredArchiveTest extends TestCase {

    private MockArchive archive;

    public void testNothingFiltered() throws Exception {

        assertEquals(archive, new FilteredArchive(archive, new Nothing()));
    }

    public void testFilterHalf() throws Exception {

        List<String> list = list(new FilteredArchive(archive, new Half()));

        assertEquals(25, list.size());
    }

    public void testFilterAll() throws Exception {

        List<String> list = list(new FilteredArchive(archive, new All()));

        assertEquals(0, list.size());
    }

    public static void assertEquals(Iterable<?> expectedList, Iterable<?> actualList) {
        final Iterator<?> expected = expectedList.iterator();
        final Iterator<?> actual = actualList.iterator();

        int i = 0;
        while (expected.hasNext() && actual.hasNext()) {
            assertEquals(expected.next(), actual.next());
            i++;
        }

        assertEquals(i + " items were the same", expected.hasNext(), actual.hasNext());
    }

    public static class Nothing implements Filter {

        @Override
        public boolean accept(String name) {
            return true;
        }
    }

    public static class All implements Filter {

        @Override
        public boolean accept(String name) {
            return false;
        }
    }

    public static class Half implements Filter {

        private boolean accept;

        @Override
        public boolean accept(String name) {
            return accept = !accept;
        }
    }

    public static <T> List<T> list(Iterable<T> iterable) {
        List<T> list = new ArrayList<T>();

        for (T t : iterable) {
            list.add(t);
        }

        return list;
    }


    @Override
    protected void setUp() throws Exception {
        // 50 entries
        archive = new MockArchive(
                "org.apache.xbean.asm.AnnotationVisitor",
                "org.apache.xbean.asm.AnnotationWriter",
                "org.apache.xbean.asm.Attribute",
                "org.apache.xbean.asm.ClassAdapter",
                "org.apache.xbean.asm.commons.AdviceAdapter",
                "org.apache.xbean.asm.commons.AnalyzerAdapter",
                "org.apache.xbean.asm.commons.GeneratorAdapter",
                "org.apache.xbean.asm.commons.JSRInlinerAdapter",
                "org.apache.xbean.asm.commons.RemappingAnnotationAdapter",
                "org.apache.xbean.asm.commons.RemappingClassAdapter",
                "org.apache.xbean.asm.commons.RemappingFieldAdapter",
                "org.apache.xbean.asm.commons.RemappingMethodAdapter",
                "org.apache.xbean.asm.commons.RemappingSignatureAdapter",
                "org.apache.xbean.asm.commons.SerialVersionUIDAdder",
                "org.apache.xbean.asm.MethodAdapter",
                "org.apache.xbean.asm.tree.AnnotationNode",
                "org.apache.xbean.asm.tree.MultiANewArrayInsnNode",
                "org.apache.xbean.finder.AnnotatedMember",
                "org.apache.xbean.finder.AnnotatedMethod",
                "org.apache.xbean.finder.AnnotatedTarget",
                "org.apache.xbean.finder.AnnotationFinder",
                "org.apache.xbean.finder.Archive",
                "org.apache.xbean.finder.BundleAnnotationFinder",
                "org.apache.xbean.finder.BundleArchive",
                "org.apache.xbean.finder.BundleAssignableClassFinder",
                "org.apache.xbean.finder.ClassesArchive",
                "org.apache.xbean.finder.ClasspathArchive",
                "org.apache.xbean.finder.MetaAnnotation",
                "org.apache.xbean.finder.PackageFilteredArchive",
                "org.apache.xbean.finder.PrefixFilteredArchive",
                "org.apache.xbean.finder.RegexFilteredArchive",
                "org.acme.bar.AnnType",
                "org.acme.bar.FullyAnnotated",
                "org.acme.bar.ParamA",
                "org.apache.xbean.finder.MetaAnnotatedClassTest",
                "org.apache.xbean.finder.MetaAnnotatedMethodTest",
                "org.apache.xbean.finder.BundleAnnotationFinder",
                "org.apache.xbean.finder.BundleAssignableClassFinder",
                "org.apache.xbean.naming.context.ContextAccess",
                "org.apache.xbean.naming.context.ContextAccessControlList",
                "org.apache.xbean.propertyeditor.ArrayConverter",
                "org.apache.xbean.propertyeditor.ArrayListEditor",
                "org.apache.xbean.propertyeditor.Inet4AddressEditor",
                "org.apache.xbean.propertyeditor.Inet6AddressEditor",
                "org.apache.xbean.propertyeditor.InetAddressEditor",
                "org.apache.xbean.recipe.AllPropertiesRecipe",
                "org.apache.xbean.recipe.ArrayRecipe",
                "org.apache.xbean.recipe.AsmParameterNameLoader",
                "org.apache.xbean.recipe.MissingAccessorException",
                "org.apache.xbean.recipe.XbeanAsmParameterNameLoader"
        );
    }
}
