;HW01_121044038.lisp
;	created by Recep Sivri on 14/10/2015
;    intersect function finds intersections two lists.
;    FindLists function finds sublists in lists if finds return T else return nil.
;    equalLists compares two lists if they are equals. 
;	 isElement is instead of member function.
;	 sameElements function compares two equal length list.
(defun intersect (A B)
	(if (eq A ()) ();if A list is empty return empty list.
		(if  (eq (listp (car A)) nil) (if (eq (isElement (car A) B) nil) (intersect (cdr A) B) (cons (car A) (intersect (cdr A) B)))
			(if (eq (FindLists (car A) B) nil) (intersect (cdr A) B)  (cons (car A) (intersect (cdr A) B)))
		);else if A is not empty list if first element is not a list call isElement function and searching first element in B	
	)    ;if not find then continue recursive.If first element is list call FindLists and search same list in B.
)
(defun FindLists (A B)
	(if(eq B ()) () 
		(if (eq (listp  (car B))  T) (if (eq (equalLists A (car B)) T) A (FindLists A (cdr B))) (FindLists A (cdr B)))
   	);if B is emty function return a empty function.If first element of list B is a list then compare two list A  and B
)    ;if A and B is not equal then continue recursive if equal return A.
(defun isElement (A B)
	(if(eq B ()) nil
		(if(eq A (car B)) T (isElement A (cdr B)))
	);this function searches an element in list A is element B is List same member function.
)
(defun equalLists (A B)
	(if(eq (length A) (length B)) (sameElements A B) nil)
);this function if lengths of two elements are same then call sameElements and determine they are same if not return nil.
(defun  sameElements (A B)
	(if(eq A ()) T
		(if (isElement (car A) B) (sameElements (cdr A) B) nil)
	);This function compares two same lenghth list then returns T if they same returns nil if they different.
)