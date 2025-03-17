class Movie {
    String title;
    String director;
    int year;
    double rating;
    Movie next;
    Movie prev;

    public Movie(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.next = null;
        this.prev = null;
    }
}

class MovieManagementSystem {
    private Movie head;
    private Movie tail;

    public void addMovieAtBeginning(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (head == null) {
            head = tail = newMovie;
        } else {
            newMovie.next = head;
            head.prev = newMovie;
            head = newMovie;
        }
    }

    public void addMovieAtEnd(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (tail == null) {
            head = tail = newMovie;
        } else {
            tail.next = newMovie;
            newMovie.prev = tail;
            tail = newMovie;
        }
    }

    public void addMovieAtPosition(String title, String director, int year, double rating, int position) {
        if (position <= 0) {
            addMovieAtBeginning(title, director, year, rating);
            return;
        }
        Movie newMovie = new Movie(title, director, year, rating);
        Movie temp = head;
        for (int i = 0; temp != null && i < position - 1; i++) {
            temp = temp.next;
        }
        if (temp == null || temp.next == null) {
            addMovieAtEnd(title, director, year, rating);
            return;
        }
        newMovie.next = temp.next;
        temp.next.prev = newMovie;
        temp.next = newMovie;
        newMovie.prev = temp;
    }

    public void removeMovieByTitle(String title) {
        Movie temp = head;
        while (temp != null && !temp.title.equalsIgnoreCase(title)) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Movie not found!");
            return;
        }
        if (temp == head) {
            head = temp.next;
            if (head != null) head.prev = null;
        } else if (temp == tail) {
            tail = temp.prev;
            if (tail != null) tail.next = null;
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }
    }

    public void searchByDirector(String director) {
        Movie temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.director.equalsIgnoreCase(director)) {
                System.out.println(temp.title + " (" + temp.year + ") - Rating: " + temp.rating);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No movies found by " + director);
    }

    public void searchByRating(double rating) {
        Movie temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.rating >= rating) {
                System.out.println(temp.title + " (" + temp.year + ") - Director: " + temp.director);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No movies found with rating " + rating + " or higher.");
    }

    public void updateRating(String title, double newRating) {
        Movie temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                temp.rating = newRating;
                System.out.println("Updated rating of " + title + " to " + newRating);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Movie not found!");
    }

    public void displayForward() {
        Movie temp = head;
        while (temp != null) {
            System.out.println(temp.title + " (" + temp.year + ") - " + temp.director + " - Rating: " + temp.rating);
            temp = temp.next;
        }
    }

    public void displayReverse() {
        Movie temp = tail;
        while (temp != null) {
            System.out.println(temp.title + " (" + temp.year + ") - " + temp.director + " - Rating: " + temp.rating);
            temp = temp.prev;
        }
    }

    public static void main(String[] args) {
        MovieManagementSystem mms = new MovieManagementSystem();
        mms.addMovieAtEnd("Inception", "Christopher Nolan", 2010, 8.8);
        mms.addMovieAtEnd("Interstellar", "Christopher Nolan", 2014, 8.6);
        mms.addMovieAtBeginning("The Dark Knight", "Christopher Nolan", 2008, 9.0);
        mms.addMovieAtPosition("Tenet", "Christopher Nolan", 2020, 7.5, 2);

        System.out.println("Movies in forward order:");
        mms.displayForward();

        System.out.println("\nMovies in reverse order:");
        mms.displayReverse();

        System.out.println("\nSearching for movies directed by Christopher Nolan:");
        mms.searchByDirector("Christopher Nolan");

        System.out.println("\nUpdating rating of Tenet:");
        mms.updateRating("Tenet", 7.8);

        System.out.println("\nRemoving 'Interstellar' from list:");
        mms.removeMovieByTitle("Interstellar");
        mms.displayForward();
    }
}