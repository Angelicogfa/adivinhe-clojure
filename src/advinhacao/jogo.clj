(ns advinhacao.jogo)50

(defn le-registro! [inicio fim]
  (do
    (println "Informe um valor entre " inicio " e " fim)
    (Integer/parseInt (read-line))))

(defn advinhe [inicio fim segredo vidas chute]
  (cond
    (= chute segredo)
    true

    (= vidas 1)
    false

    (or (< chute inicio)
        (> chute fim))
    [inicio fim segredo vidas]

    (< chute segredo)
    [chute fim segredo (dec vidas)]

    :else
    [inicio chute segredo (dec vidas)]))

(= (advinhe 1 100 33 1 30) false)
(= (advinhe 30 100 78 2 78) true)
(= (advinhe 10 100 33 2 20) [20 100 33 1])
(= (advinhe 10 100 33 4 5) [10 100 33 4])
(= (advinhe 30 100 33 4 50) [30 50 33 3])

(defn inicia-jogo [[inicio fim segredo vidas]]
  (let [chute (le-registro! inicio fim)
        resultado (advinhe inicio fim segredo vidas chute)]
    (cond
      (true? resultado)
      (println "Voce ganhou")

      (false? resultado)
      (println "Voce perdeu, o valor era: " segredo)

      :else
      (recur resultado))))

(defn -main []
  (inicia-jogo [1 100 (inc (rand-int 100)) 5]))