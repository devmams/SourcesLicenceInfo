#= NOM1 - Prénom 1
   NOM2 - Prénom 2
   N'oubliez pas de modifier ce commentaire, ainsi que le nom du fichier! =#

using JuMP, GLPKMathProgInterface


#= Nombreuses autres fonctions à ajouter
   .
   .
   .
   .
   .
=#

function constCycleRec(TLiens::Vector{Vector{Int}}, cycle::Vector{Int}, PointUtilises::Vector{Int} ,pointdeb::Int ,pointCour::Int)
    if( TLiens[pointCour][2] == pointdeb)
        push!(cycle,pointCour)
        push!(PointUtilises,pointCour)
        return cycle
    else
        return constCycleRec(TLiens,push!(cycle,pointCour),push!(PointUtilises,pointCour),pointdeb,TLiens[pointCour][2])
    end
end

function appartient(PointUtilises::Vector{Int}, ind::Int)
    for i in 1:size(PointUtilises,1)
        if(PointUtilises[i] == ind)
            return true
        end
    end
    return false
end

function constCycles(T::Vector{Vector{Int}})
    TLiens = T
    PointUtilises = Vector{Int}(0)
    Tcycles = Vector{Vector{Int}}(0)
    ind = 1

    while ind <= size(TLiens,1)
        if(appartient(PointUtilises,ind))
            ind = ind + 1
        else
            push!(PointUtilises,ind)
            push!(Tcycles,constCycleRec(TLiens,[ind],PointUtilises,ind,TLiens[ind][2]))
            ind = ind + 1
        end
    end

    return Tcycles
end

function resolution_exacte(solverSelected, C::Array{Int,2})
    m = Model(solver = solverSelected)
    nbLieu= size(C,1)
    @variable(m,x[1:nbLieu, 1:nbLieu] >= 0, Bin)
    @objective(m, Min, sum(C[i,j]x[i,j] for i in 1:nbLieu, j in 1:nbLieu) )
    @constraint(m, ctr1[i=1:nbLieu], sum(x[i,j] for j in 1:nbLieu if (i != j)) == 1)
    @constraint(m, ctr2[j=1:nbLieu], sum(x[i,j] for i in 1:nbLieu if (i != j)) == 1)

    status = solve(m)
    x = m[:x]
    T  = Vector{Vector{Int}}(0)
    for i in 1:size(C,1), j in 1:size(C,1)
        if isapprox(1.0,getvalue(x[i,j]))
            push!(T,[i,j])
        end
    end
    println(T)
    Tc = constCycles(T)
    println(Tc)

    while size(Tc,1) > 1

        t = Tc[indmin([size(i) for i in Tc])]
        expr = AffExpr()
        for j in 1:size(t,1)
            if j == size(t,1)
                push!(expr,1.0,x[t[j],t[1]])
            else
                push!(expr,1.0,x[t[j],t[j+1]])
            end
        end
        println("#")
        con = @constraint(m,expr <= size(t,1) - 1 )

        status = solve(m)
        x = m[:x]
        T  = Vector{Vector{Int}}(0)
        for i in 1:size(C,1), j in 1:size(C,1)
            if isapprox(1.0,getvalue(x[i,j]))
                push!(T,[i,j])
            end
        end
        println(T)
        Tc = constCycles(T)
        println(Tc)
    end
    println(getobjectivevalue(m))
    return m
end

function delta(C::Array{Int,2},i1::Int,j1::Int,i2::Int,j2::Int)
    return (C[i1,i2] + C[j1,j2] - C[i1,j1] - C[i2,j2])
end

function resolution_approchee(C::Array{Int,2})

    nbLieu = size(C,1)
    T  = Vector{Vector{Int}}(0)
    for i in 1:nbLieu
        if i == nbLieu
            push!(T,[i,1])
        else
            push!(T,[i,i+1])
        end
    end
    println(T)
    r1 = rand(1:(nbLieu-2))
    i1 = r1
    j1 = r1+1
    if(i1 == 1)
        r2 = rand((j1+1):(nbLieu-1))
    else r2 = rand((j1+1):nbLieu)
    end

    i2 = r2
    j2 = r2+1
    if(i2 == nbLieu)
        j2 = 1
    end
    println(i1)
    println(j1)
    println(i2)
    println(j2)
    d = delta(C,i1,j1,i2,j2)
    println(d)
    if (d < 0)
        t1 = [i1,i2]
        t2 = [j1,j2]
        T  = Vector{Vector{Int}}(0)
        for i in 1:(i1-1)
            push!(T,[i,i+1])
        end
        push!(T,t1)
        ind = i2
        while ind != j1
            push!(T,[ind,ind-1])
            ind = ind - 1
        end
        push!(T,t2)
        if j2 != 1
            for i in j2:nbLieu
                if i == nbLieu
                    push!(T,[i,1])
                else
                    push!(T,[i,i+1])
                end
            end
        end
    end
    println(T)

end

# Fonction de résolution exacte du problème de voyageur de commerce, dont le distancier est passé en paramètre

function TSP(C::Array{Int,2})
end

#= Fonction qui résout l'ensemble des instances du projet avec la méthode de résolution exacte,
   le temps d'exécution de chacune des instances est mesuré =#

function scriptTSP()
    # Première exécution sur l'exemple pour forcer la compilation si elle n'a pas encore été exécutée
    C = parseTSP("plat/exemple.dat")
    TSP(C)

    # Série d'exécution avec mesure du temps pour les instances symétriques
    for i in 10:10:150
        file = "plat/plat" * string(i) * ".dat"
        C = parseTSP(file)
        println("Instance à résoudre : plat",i,".dat")
        @time TSP(C)
    end

    # Série d'exécution avec mesure du temps pour les instances asymétriques
    for i in 10:10:150
        file = "relief/relief" * string(i) * ".dat"
        println("Instance à résoudre : relief",i,".dat")
        C = parseTSP(file)
        @time TSP(C)
    end
end

# fonction qui prend en paramètre un fichier contenant un distancier et qui retourne le tableau bidimensionnel correspondant

function parseTSP(nomFichier::String)
    # Ouverture d'un fichier en lecture
    f = open(nomFichier,"r")

    # Lecture de la première ligne pour connaître la taille n du problème
    s = readline(f) # lecture d'une ligne et stockage dans une chaîne de caractères
    tab = parse.(Int,split(s," ",keep = false)) # Segmentation de la ligne en plusieurs entiers, à stocker dans un tableau (qui ne contient ici qu'un entier)
    n = tab[1]

    # Allocation mémoire pour le distancier
    C = Array{Int,2}(n,n)

    # Lecture du distancier
    for i in 1:n
        s = readline(f)
        tab = parse.(Int,split(s," ",keep = false))
        for j in 1:n
            C[i,j] = tab[j]
        end
    end

    # Fermeture du fichier
    close(f)

    # Retour de la matrice de coûts
    return C
end

C = parseTSP("plat/exemple.dat")

println("----------------")
# C = parseTSP("plat/plat10.dat")
# resolution_exacte(GLPKSolverMIP(),C)
#
# println("----------------")
# C = parseTSP("relief/relief10.dat")

println("-------exacte---------")
# resolution_exacte(GLPKSolverMIP(),C)
println("-------approchee---------")
resolution_approchee(C)

# scriptTSP()
