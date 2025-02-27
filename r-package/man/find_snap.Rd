% Generated by roxygen2: do not edit by hand
% Please edit documentation in R/find_snap.R
\name{find_snap}
\alias{find_snap}
\title{Find snapped locations of input points on street network}
\usage{
find_snap(r5r_core, points, mode = "WALK")
}
\arguments{
\item{r5r_core}{a rJava object to connect with R5 routing engine}

\item{points}{a spatial sf POINT object, or a data.frame
containing the columns 'id', 'lon', 'lat'}

\item{mode}{string. Defaults to "WALK", also allows "BICYCLE", and "CAR".}
}
\value{
A data.table with the original points as well as their respective
snapped coordinates on the street network and the Euclidean distance
between original points and their respective snapped location. Points
that could not be snapped show \code{NA} coordinates and \code{found = FALSE}.
}
\description{
R5 tries to snap origin and destination points to the street
network in two rounds. First, it uses a search radius of 300 meters. If the
first round is unsuccessful, then R5 expands the search radius to 1.6 km.
Points that aren't linked to the street network after those two rounds are
returned with \code{NA} coordinates and \code{found = FALSE}. Please note that the
location of the snapped points depends on the transport mode set by the user.
}
\examples{
if (interactive()) {

library(r5r)

# build transport network
path <- system.file("extdata/spo", package = "r5r")
r5r_core <- setup_r5(data_path = path)

# load origin/destination points
points <- read.csv(file.path(path, "spo_hexgrid.csv"))

# find where origin or destination points are snapped
snap_df <- find_snap(r5r_core,
                     points = points,
                     mode = 'CAR')

stop_r5(r5r_core)
}
}
\seealso{
Other support functions: 
\code{\link{assert_decay_function}()},
\code{\link{assert_points_input}()},
\code{\link{check_connection}()},
\code{\link{download_metadata}()},
\code{\link{posix_to_string}()},
\code{\link{select_mode}()},
\code{\link{set_max_lts}()},
\code{\link{set_max_rides}()},
\code{\link{set_max_street_time}()},
\code{\link{set_n_threads}()},
\code{\link{set_speed}()},
\code{\link{set_suboptimal_minutes}()},
\code{\link{set_verbose}()},
\code{\link{stop_r5}()},
\code{\link{street_network_to_sf}()}
}
\concept{support functions}
