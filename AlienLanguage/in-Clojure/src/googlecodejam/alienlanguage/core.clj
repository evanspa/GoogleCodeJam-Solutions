(ns
    ^{:doc "Collection of functions for solving the 'Alien Language'
Google Code Jam challenge."
      :author "Paul Evans"}
  googlecodejam.alienlanguage.core
  (import (org.apache.commons.lang3 StringUtils)))

(defn has-left-delim? [x]
  (> (count (re-seq #"[\(\[\{\<]" x)) 0))

(defn has-right-delim? [x]
  (> (count (re-seq #"[\)\}\]\>]" x)) 0))

(defn has-delim? [x]
  (or (has-left-delim? x)
      (has-right-delim? x)))

(defn template-to-seq
  "docs"
  [template template-open-delim template-close-delim]
  (if (nil? template)
    '()
    (if (= 0 (count (re-seq #"[\(\)\[\]\{\}\<\>]" template)))
      (map #(seq (str %1)) template)
      (loop [template (seq template)
             elements (vector)]
        (let [char (first template)]
          (if (nil? char)
            (seq elements)
            (if (has-left-delim? (str char))
              (recur (seq (StringUtils/substringAfter
                           (apply str template)
                           (str template-close-delim)))
                     (conj elements
                           (seq (StringUtils/substringBetween
                                 (apply str template)
                                 (str template-open-delim)
                                 (str template-close-delim)))))
              (recur (rest template)
                     (conj elements (seq (str char)))))))))))

(defn is-match?
  "Returns true if the candidate string matches against the
   template string.  Both candidate and template are strings.
   candidate will be a string that will only consist of non-numeric
   characters.  Examples of candidate are:

     \"abc\"
     \"def\"
     \"adrzy\"

   template will be a string that will consist of both non-numeric
   characters, but also can contain sub-groups of non-numeric
   characters.  A sub-group represences a sequence of allowed
   characters for that index of the string.  Examples of template
   are:

     \"a(bc)c\"
     \"abc\"
     \"a(bdc)ab\"

   In the 3rd example, 'a(bdc)ab', the '(bdc)' should logically be
   looked at as a single character, that can be one of 3 posslble
   values, 'a', 'd' or 'c'.

   template-open-delim is a character that starts a sub-group found
   within template.  In the examples above, '(' is the open delimeter.
   ')' is the close delimeter.

   template-open-delim should be one of: (,{,[,<
   template-close-delim should be one of: ),},],>"
  ([candidate template template-open-delim template-close-delim]
       ; sanity checks on candidate
     (if (has-delim? candidate)
       (throw (Exception. "Invalid character(s) found in candidate"))
       (if (not (has-left-delim? (str template-open-delim)))
         (throw (Exception. "Template open-delimeter is not valid"))
         (if (not (has-right-delim? (str template-close-delim)))
           (throw (Exception. "Template close-delimeter is not valid"))
           (let [candidate-as-seq (seq candidate)
                 template-as-seq (template-to-seq
                                  template
                                  template-open-delim
                                  template-close-delim)]
             (= candidate-as-seq
                (map #(some #{%1} %2)
                     candidate-as-seq
                     template-as-seq)))))))
  ([candidate template]
     (is-match? candidate template \( \))))

(defn matches
  ([candidates-coll template template-open-delim template-close-delim]
     (map #(is-match? %
                      template
                      template-open-delim
                      template-close-delim) candidates-coll))
  ([candidates-coll template]
     (matches candidates-coll template \( \))))

(defn count-matches
  ([candidates-coll template template-open-delim template-close-delim]
     (count (filter true?
                    (matches
                     candidates-coll
                     template
                     template-open-delim
                     template-close-delim))))
  ([candidates-coll template]
     (count-matches candidates-coll template \( \))))
