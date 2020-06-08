package rokh.dragonfly;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.collections4.CollectionUtils;

/**
 * Training class, purpose is to see and list what kind of helper classes can be used in everyday work
 * School have always teaching me to do things myself... but IRL it doesn't work like this, does it ;) ?
 */
public class Train_HelperClasses {

    public static void train_HelperClasses() {

        /**
         * apache commons libs / jars
         *
         * org.apache.commons.lang3.StringUtils & org.apache.commons.lang3.ArrayUtils
         * download : http://commons.apache.org/proper/commons-lang/download_lang.cgi ~ 523 kB
         * javadoc : http://commons.apache.org/proper/commons-lang/javadocs/api-3.1/org/apache/commons/lang3/
         *
         * org.apache.commons.collections4.CollectionUtils
         * download : http://commons.apache.org/proper/commons-collections/download_collections.cgi ~ 752 kB
         * javadoc : https://commons.apache.org/proper/commons-collections/apidocs/org/apache/commons/collections4/CollectionUtils.html
         */

        // ======= org.apache.commons.lang3.StringUtils ================================================================
        // http://commons.apache.org/proper/commons-lang/javadocs/api-3.1/org/apache/commons/lang3/StringUtils.html

        // ======= StringUtils constants ===============================================================================
        // StringUtils.EMPTY : can be use to check if a String is empty : StringUtils.EMPTY.equals(data)
        // StringUtils.SPACE : can be use to add a space to a String : StringUtils.SPACE + data

        // ======= StringUtils functions ===============================================================================
        // StringUtils.equals() : compares two strings null-safe.
        // StringUtils.startsWith() : check if a String starts with a prefix null-safe.
        // StringUtils.endsWith() : check if a String ends with a suffix null-safe.
        // StringUtils.contains() : check if CharSequence contains a search CharSequence, handling null.
        // StringUtils. containsOnly/containsNone/containsAny : check if it contains only/none/any of these characters.
        // allow to check : isEmpty, isBlank, isAlpha, isNumeric, isAlphanumeric, ...
        // do work on it : trim, substring, split, join, upperCase, lowerCase, capitalize, ...

        // example : "yes"
        String data = "prefixeIamDATA";
        if (StringUtils.startsWith(data, "prefixeIam")) {
            print("yes");
        } else {
            print("no");
        }

        // ======= org.apache.commons.lang3.ArrayUtils =================================================================
        // http://commons.apache.org/proper/commons-lang/javadocs/api-3.1/org/apache/commons/lang3/ArrayUtils.html

        // ======= ArrayUtils constants ================================================================================
        // ArrayUtils, none are really useful

        // ======= ArrayUtils functions ================================================================================
        // ArrayUtils.clone() : clones an array returning a typecast result and handling null.
        // ArrayUtils.contains() : check if the value is in the given array.
        // ArrayUtils.indexOf() : finds the index of the given value in the array.
        // allow to check : isEmpty, isNotEmpty, isSameLength, ...
        // do work on it : reverse, subarray, ...

        // example : "yes"
        String table[] = {"Dani", "Illi", "Alex", "Sili", "Rokh"};
        if (4 == ArrayUtils.indexOf(table, "Rokh")) {
            print("yes");
        } else {
            print("no");
        }

        // ======= org.apache.commons.collections4.CollectionUtils =====================================================
        // https://commons.apache.org/proper/commons-collections/apidocs/org/apache/commons/collections4/CollectionUtils.html

        // ======= CollectionUtils constants ===========================================================================
        // CollectionUtils, none are really useful

        // ======= CollectionUtils functions ===========================================================================
        // CollectionUtils.addAll(Collection<C> collection, C... elements) :
        //     Adds all elements in the array to the given collection.
        // CollectionUtils.collect(Iterable<? extends I> inputCollection, Transformer<? super I,? extends O> transformer, R outputCollection) :
        //     Transforms all elements from input collection with the given transformer and adds them to the output collection.
        // CollectionUtils.filter(Iterable<T> collection, Predicate<? super T> predicate) :
        //     Filter the collection by applying a Predicate to each element. Remove element if false.
        // CollectionUtils.filterInverse(Iterable<T> collection, Predicate<? super T> predicate) :
        //     Filter the collection by applying a Predicate to each element. Remove element if true.
        //
        // JOIN, INTERSECTION, UNION, MINUS, DISJUNCTION
        //
        // CollectionUtils.collate(Iterable<? extends O> a, Iterable<? extends O> b, boolean includeDuplicates) :
        //     Merges two sorted Collections, a and b, into a single, sorted List such that the natural ordering of the
        //     elements is retained.
        // CollectionUtils.intersection(Iterable<? extends O> a, Iterable<? extends O> b) :
        //     Returns a Collection containing the intersection of the given Iterables.
        // CollectionUtils.union(Iterable<? extends O> a, Iterable<? extends O> b) :
        //     Returns a Collection containing the union of the given Iterables.
        // CollectionUtils.subtract(Iterable<? extends O> a, Iterable<? extends O> b, Predicate<O> p) :
        //     Returns a new Collection containing a minus a subset of b.
        // CollectionUtils.disjunction(Iterable<? extends O> a, Iterable<? extends O> b) :
        //     Returns a Collection containing the exclusive disjunction (symmetric difference) of the given Iterables.
        //
        // allow to check :
        //     get(Object object, int index)
        //     isEmpty(Collection<?> collection)
        //     isNotEmpty(Collection<?> collection)
        //     isSubCollection(Collection<?> a, Collection<?> b)
        //     containsAll(Collection<?> coll1, Collection<?> coll2)
        //     containsAny(Collection<?> coll1, Collection<?> coll2)
        //     isEqualCollection(Collection<?> a, Collection<?> b)

        // example : ... TODO
    }

    public static void print(String s) {
        System.out.println(s);
    }
}
