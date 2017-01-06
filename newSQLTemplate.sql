/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  PaulsBook
 * Created: 04.01.2017
 */


SELECT * FROM category c, question q, category2question c2q WHERE category_id = c.id and question_id = q.id and c.id = 3 ORDER by q.id