(ns hello-bars.core
  (:use-macros [c2.util :only [bind!]])
  (:use [c2.core :only [unify]]
        )
  
  (:require [c2.scale :as scale]
            [c2.event :as event]))


(defn my_func []
  (str "js/alert" "ja"))

(bind! "#bars" 
  (let [width 500 bar-height 20
        data {"A" 1, "B" 2, "C" 4, "D" 3}
        s (scale/linear :domain [0 (apply max (vals data))]
                        :range [0 width])]

    [:div
     (unify data (fn [[label val]]
                   [:div.pepe {:style {:height bar-height
                                  :width (s val)
                                  :background-color "gray" :class "pepe"}
                          }
                    
                      [:span {:style {:color "white"}} label]]))]))

(event/on "#bars" ".pepe" :click
          (fn [[a b] d  _ e]
            (js/console.log (str (apply str a) " - " b))
            ))
